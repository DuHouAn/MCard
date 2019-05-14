package com.southeast.passbook.service;

import com.southeast.passbook.vo.Pass;
import com.southeast.passbook.vo.Response;

/**
 * <h1>获取用户个人优惠券信息</h1>
 * Created by 18351 on 2019/5/13.
 */
public interface IUserPassService {

    /**
     * 获取用户个人优惠券信息，即 "我的优惠券" 功能实现
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserPassInfo(Long userId) throws Exception;

    /**
     * 获取用户已消费的优惠券信息，即 “已使用优惠券”功能实现
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserUsedPassInfo(Long userId) throws Exception;

    /**
     * 获取用户所有的优惠券
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserAllPassInfo(Long userId) throws Exception;

    /**
     * 用户使用优惠券
     * @param pass {@link Pass}
     * @return {@link Response}
     */
    Response userUsePass(Pass pass);
}
