package com.southeast.passbook.vo;

import com.southeast.passbook.constants.ErrorCode;
import com.southeast.passbook.dao.MerchantsDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1>投放优惠券的对象定义</h1>
 * Created by 18351 on 2019/5/7.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {
    private Integer id; //所属商户 id

    private String title; // 优惠券标题

    private String summary; // 优惠券摘要

    private String desc; //优惠券的详细信息

    private Long limit ; //最大个数限制

    private Boolean hasToken;
    // 优惠券是否有 token，用于商户核销
    // token 存储如 redis Set 中，每次领取从 redis 中获取

    private Integer background; //优惠券背景色

    private Date start; // 优惠券开始时间

    private Date end; // 优惠券结束时间

    /**
     * 校验优惠券对象的有效性
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(null == merchantsDao.findById(id)){ //没有找到，说明商户信息不存在
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }
}
