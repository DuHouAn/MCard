package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;
import com.southeast.passbook.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户服务测试</h1>
 * Created by 18351 on 2019/5/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testCreateUser() throws Exception {

        User user = new User();
        user.setBaseInfo(
                new User.BaseInfo("southeast", 10, "m")
        );
        user.setOtherInfo(
                new User.OtherInfo("123456", "北京市朝阳区")
        );

        // {"data":{"baseInfo":{"age":10,"name":"imooc","sex":"m"},
        // "id":149276,
        // "otherInfo":{"address":"北京市朝阳区","phone":"123456"}},
        // "errorCode":0,"errorMsg":""}
        System.out.println(JSON.toJSONString(userService.createUser(user)));
    }
}
