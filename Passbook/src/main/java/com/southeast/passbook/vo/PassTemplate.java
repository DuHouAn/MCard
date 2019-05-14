package com.southeast.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1> 投放的优惠券对象定义 </h1>
 * Created by 18351 on 2019/5/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {

    private Integer id; //所属商品 id

    private String title; //优惠券标题

    private String summary; //优惠券摘要

    private String desc; //优惠券详细信息

    private Long limit; //最大个数限制

    private Boolean hasToken; //优惠券是否有 Token, 用于商户核销

    private Integer background; //优惠券背景色

    private Date start; //优惠券开始时间

    private Date end; //优惠券结束时间
}
