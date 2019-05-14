package com.southeast.passbook.vo;

import com.southeast.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <h1>优惠券模板信息</h1>
 * Created by 18351 on 2019/5/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplateInfo{

    private PassTemplate passTemplate; //优惠券模板

    private Merchants merchants; //对应的商户信息
}
