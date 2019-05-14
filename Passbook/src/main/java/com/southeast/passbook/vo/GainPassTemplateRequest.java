package com.southeast.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户领取优惠券的请求对象</h1>
 * 用户领取那些 PassTemplate
 * Created by 18351 on 2019/5/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GainPassTemplateRequest {

    private Long userId; //用户 id

    private PassTemplate passTemplate; // PassTemplate 对象
}
