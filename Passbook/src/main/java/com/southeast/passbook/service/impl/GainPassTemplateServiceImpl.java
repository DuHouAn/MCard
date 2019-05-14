package com.southeast.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.southeast.passbook.constant.Constants;
import com.southeast.passbook.mapper.PassTemplateRowMapper;
import com.southeast.passbook.service.IGainPassTemplateService;
import com.southeast.passbook.utils.RowKeyGenUtil;
import com.southeast.passbook.vo.GainPassTemplateRequest;
import com.southeast.passbook.vo.PassTemplate;
import com.southeast.passbook.vo.Response;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>用户领取优惠券功能实现</h1>
 * Created by 18351 on 2019/5/13.
 */
@Slf4j
@Service
public class GainPassTemplateServiceImpl implements IGainPassTemplateService{

    private final HbaseTemplate hbaseTemplate; //HBase 客户端

    private final StringRedisTemplate redisTemplate; // redis 客户端

    @Autowired
    public GainPassTemplateServiceImpl(HbaseTemplate hbaseTemplate,
                                       StringRedisTemplate redisTemplate) {
        this.hbaseTemplate = hbaseTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Response gainPassTemplate(GainPassTemplateRequest request) throws Exception {
        PassTemplate passTemplate;

        String passTemplateId = RowKeyGenUtil.genPassTemplateRowKey(
                request.getPassTemplate()); //获取 passTemplateId,用于获取 PassTemplate 对象

        //======校验环节========
        try{
            //通过 Habse 客户端获取 passTemplate 对象
            passTemplate = hbaseTemplate.get(
                    Constants.PassTemplateTable.TABLE_NAME,
                    passTemplateId,
                    new PassTemplateRowMapper()
            );
        }catch (Exception e){
            //校验是否获取优惠券
            log.error("Gain PassTemplate Error: {}", JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("Gain PassTemplate Error!");
        }

        //passTemplate.getLimit() == -1，说明优惠券的数量不受限制
        //passTemplate.getLimit() <= 1,此时不能领取优惠券
        if (passTemplate.getLimit() <= 1 && passTemplate.getLimit() != -1) {
            log.error("PassTemplate Limit Max: {}",
                    JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("PassTemplate Limit Max!");
        }

        // 优惠券只能在有效期内领取
        Date cur = new Date();
        if (!(cur.getTime() >= passTemplate.getStart().getTime()
                && cur.getTime() < passTemplate.getEnd().getTime())) {
            log.error("PassTemplate ValidTime Error: {}",
                    JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("PassTemplate ValidTime Error!");
        }
        //=======校验环节========

        // 减去优惠券的 limit
        if (passTemplate.getLimit() != -1) {  //优惠券数量有限制，才考虑减去 limit 操作
            List<Mutation> datas = new ArrayList<>();
            byte[] FAMILY_C = Constants.PassTemplateTable.FAMILY_C.getBytes();
            byte[] LIMIT = Constants.PassTemplateTable.LIMIT.getBytes();

            Put put = new Put(Bytes.toBytes(passTemplateId));
            put.addColumn(FAMILY_C, LIMIT, Bytes.toBytes(passTemplate.getLimit() - 1));
            datas.add(put);

            hbaseTemplate.saveOrUpdates(Constants.PassTemplateTable.TABLE_NAME, datas);
        }

        // 将优惠券保存到用户优惠券表 -- pass 表中
        if (!addPassForUser(request, passTemplate.getId(), passTemplateId)) {
            return Response.failure("GainPassTemplate Failure!");
        }

        return Response.success();
    }

    /**
     * 给用户添加优惠券
     * --对应用户的 pass 表
     * @param request {@link GainPassTemplateRequest} 用户请求获取优惠券
     * @param merchantsId 商户 id
     * @param passTemplateId 优惠券 id
     * @return true/false
     * @throws Exception
     */
    private boolean addPassForUser(GainPassTemplateRequest request,
                                   Integer merchantsId, String passTemplateId) throws Exception {
        byte[] FAMILY_I = Constants.PassTable.FAMILY_I.getBytes();
        byte[] USER_ID = Constants.PassTable.USER_ID.getBytes();
        byte[] TEMPLATE_ID = Constants.PassTable.TEMPLATE_ID.getBytes();
        byte[] TOKEN = Constants.PassTable.TOKEN.getBytes();
        byte[] ASSIGNED_DATE = Constants.PassTable.ASSIGNED_DATE.getBytes();
        byte[] CON_DATE = Constants.PassTable.CON_DATE.getBytes();

        List<Mutation> datas = new ArrayList<>();

        Put put = new Put(Bytes.toBytes(RowKeyGenUtil.genPassRowKey(request)));
        put.addColumn(FAMILY_I, USER_ID, Bytes.toBytes(request.getUserId()));
        put.addColumn(FAMILY_I, TEMPLATE_ID, Bytes.toBytes(passTemplateId));

        if(request.getPassTemplate().getHasToken()){ // token 已经存在
            String token = redisTemplate.opsForSet().pop(passTemplateId);
            if (null == token) {
                log.error("Token not exist: {}", passTemplateId);
                return false;
            }
            recordTokenToFile(merchantsId, passTemplateId, token); //取出 token,将token 存入文件中
            put.addColumn(FAMILY_I, TOKEN, Bytes.toBytes(token)); //从 redis 中取出token ，放入 HBase中
        }else{
            put.addColumn(FAMILY_I, TOKEN, Bytes.toBytes("-1"));
        }

        put.addColumn(FAMILY_I, ASSIGNED_DATE,
                Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(new Date()))); //领取日期，就是当前时间
        put.addColumn(FAMILY_I, CON_DATE, Bytes.toBytes("-1")); //还未消费，所以值设为 -1

        datas.add(put);

        hbaseTemplate.saveOrUpdates(Constants.PassTable.TABLE_NAME, datas);

        return true;
    }

    /**
     * 将已使用的 token 记录到文件中
     * @param merchantsId 商户 id
     * @param passTemplateId 优惠券 id
     * @param token 分配的优惠券的 token
     * @throws Exception
     */
    private void recordTokenToFile(Integer merchantsId, String passTemplateId,
                                   String token) throws Exception {
        Files.write(
                Paths.get(Constants.TOKEN_DIR, String.valueOf(merchantsId),
                        passTemplateId + Constants.USED_TOKEN_SUFFIX), //文件路径
                (token + "\n").getBytes(), //要写入的内容
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }
}
