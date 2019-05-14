package com.southeast.passbook.utils;

import com.southeast.passbook.vo.Feedback;
import com.southeast.passbook.vo.GainPassTemplateRequest;
import com.southeast.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <h1>Row Key 生成器</h1>
 * Created by 18351 on 2019/5/12.
 */
@Slf4j
public class RowKeyGenUtil {
    /**
     * 根据提供的 passTemplate 对象生成 RowKey
     * @param passTemplate {@link PassTemplate}
     * @return String Row Key
     */
    public static String genPassTemplateRowKey(PassTemplate passTemplate){
        //这里的 passTemplate 的 id 和 title 都是唯一的
        String passInfo = String.valueOf(passTemplate.getId())+"_"+ passTemplate.getTitle();
        //hbase 是一个集群，基于 rowKey 存储，rowKey 尽量分散 -> 负载均衡
        String rowKey = DigestUtils.md5Hex(passInfo);
        log.info("GenPassTemplateRowKey: {},{}",passInfo,rowKey);

        return rowKey;
    }

    /**
     * 根据领取的优惠券请求生成 RowKey,只可以在领取优惠券的时候使用
     * Pass RowKey = reversed(userId) +
     *               inverse(timestamp) +
     *               PassTemplate Row Key;
     * @param request {@link GainPassTemplateRequest}
     * @return String RowKey
     */
    public static String genPassRowKey(GainPassTemplateRequest request){
        return new StringBuilder(String.valueOf(request.getUserId())).reverse().toString()
                + (Long.MAX_VALUE - System.currentTimeMillis())
                + genPassTemplateRowKey(request.getPassTemplate());
    }

    /**
     * 根据 Feedback 构造 RowKey
     * @param feedback {@link Feedback}
     * @return String RowKey
     */
    public static String genFeedbackRowKey(Feedback feedback){

        // rowKey 包含用户id，同一用户的 feedback 存储在相近位置
        // reverse() 当数据量比较大时，id 的前缀都相同，显然后缀是不同的， reverse() 后，rowkey 可尽量分散
        // 保证了对同一 userId，越晚创建，rowKey 就值在前
        return new StringBuilder(String.valueOf(feedback.getUserId())).reverse().toString() +
                (Long.MAX_VALUE - System.currentTimeMillis());
    }
}
