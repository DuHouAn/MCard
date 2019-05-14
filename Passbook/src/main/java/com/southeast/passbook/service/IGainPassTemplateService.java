package com.southeast.passbook.service;

import com.southeast.passbook.vo.GainPassTemplateRequest;
import com.southeast.passbook.vo.Response;

/**
 * <h1>用户获取优惠券功能实现</h1>
 * Created by 18351 on 2019/5/13.
 */
public interface IGainPassTemplateService {
    /**
     * 用户领取优惠券
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    Response gainPassTemplate(GainPassTemplateRequest request) throws Exception;
}
