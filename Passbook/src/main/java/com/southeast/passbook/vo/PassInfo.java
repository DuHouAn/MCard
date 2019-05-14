package com.southeast.passbook.vo;

import com.southeast.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户领取的优惠券信息</h1>
 * Created by 18351 on 2019/5/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassInfo {

    private Pass pass; //优惠券

    private PassTemplate passTemplate; //优惠券模板

    private Merchants merchants; //优惠券对应的商户
}
