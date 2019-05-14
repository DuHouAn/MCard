package com.southeast.passbook.controller;

import com.southeast.passbook.log.LogConstants;
import com.southeast.passbook.log.LogGenerator;
import com.southeast.passbook.service.IUserService;
import com.southeast.passbook.vo.Response;
import com.southeast.passbook.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>创建用户服务</h1>
 * Created by 18351 on 2019/5/13.
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class CreateUserController {

    private final IUserService userService; //创建用户服务

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public CreateUserController(IUserService userService,
                                HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
    }


    /**
     * 创建用户
     * @param user {@link User}
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/createuser")
    Response createUser(@RequestBody User user) throws Exception{

        LogGenerator.genLog(
                httpServletRequest,
                -1L,
                LogConstants.ActionName.CREATE_USER,
                user
        );
        return userService.createUser(user);
    }
}