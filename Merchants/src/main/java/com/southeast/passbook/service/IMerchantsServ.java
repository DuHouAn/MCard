package com.southeast.passbook.service;

import com.southeast.passbook.vo.CreateMerchantsRequest;
import com.southeast.passbook.vo.PassTemplate;
import com.southeast.passbook.vo.Response;

/**
 * <h1>对商户服务接口定义</h1>
 * Created by 18351 on 2019/5/8.
 */
public interface IMerchantsServ {
    /**
     * 创建商户服务
     * @param request {@link CreateMerchantsRequest} 创建商户应用的请求
     * @return {@link Response}
     */
    Response createMerchants(CreateMerchantsRequest request);

    /**
     * 根据 id 构造商户信息
     * @param id 商户 id
     * @return {@link Response}
     */
    Response buildMerchantsInfoById(Integer id);

    /**
     * 投放优惠券
     * @param template {@link PassTemplate} 优惠券对象
     * @return {@link Response}
     */
    Response dropPassTemplate(PassTemplate template);
}
