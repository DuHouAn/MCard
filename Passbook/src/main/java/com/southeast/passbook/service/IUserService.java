package com.southeast.passbook.service;

import com.southeast.passbook.vo.Response;
import com.southeast.passbook.vo.User;

/**
 * <h1>用户服务 </h1>
 * 创建 User
 *  Created by 18351 on 2019/5/12.
 */
public interface IUserService {
    /**
     * 创建用户
     * @param user {@link User}
     * @return {@link User}
     * @throws Exception
     */
    Response createUser(User user) throws Exception;
}
