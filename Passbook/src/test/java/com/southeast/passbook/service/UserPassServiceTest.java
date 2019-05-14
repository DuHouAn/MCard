package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;

import com.southeast.passbook.vo.Pass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户优惠券服务测试</h1>
 * Created by 18351 on 2019/5/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPassServiceTest extends AbstractServiceTest {

    @Autowired
    private IUserPassService userPassService;

//    {
//        "data": [
//        {
//            "merchants": {
//            "address": "北京市",
//                    "businessLicenseUrl": "www.imooc.com",
//                    "id": 9,
//                    "isAudit": true,
//                    "logoUrl": "www.imooc.com",
//                    "name": "慕课",
//                    "phone": "1234567890"
//        },
//            "pass": {
//            "assignedDate": 1515513600000,
//                    "templateId": "3617cf73e7a1099097242115042cb7b0",
//                    "token": "token-1",
//                    "userId": 149276
//        },
//            "passTemplate": {
//            "background": 2,
//                    "desc": "详情: 慕课",
//                    "end": 1529424000000,
//                    "hasToken": true,
//                    "id": 9,
//                    "limit": 9998,
//                    "start": 1527696000000,
//                    "summary": "简介: 慕课",
//                    "title": "慕课-2"
//        }
//        }
//  ],
//        "errorCode": 0,
//        "errorMsg": ""
//    }
    @Test
    public void testGetUserPassInfo() throws Exception {

        System.out.println(JSON.toJSONString(
                userPassService.getUserPassInfo(userId))
        );
    }

    // {"data":[],"errorCode":0,"errorMsg":""}
    @Test
    public void testGetUserUsedPassInfo() throws Exception {

        System.out.println(JSON.toJSONString(
                userPassService.getUserUsedPassInfo(userId)
        ));
    }

//    {
//        "data": [
//        {
//            "merchants": {
//            "address": "北京市",
//                    "businessLicenseUrl": "www.imooc.com",
//                    "id": 9,
//                    "isAudit": true,
//                    "logoUrl": "www.imooc.com",
//                    "name": "慕课",
//                    "phone": "1234567890"
//        },
//            "pass": {
//            "assignedDate": 1515513600000,
//                    "templateId": "3617cf73e7a1099097242115042cb7b0",
//                    "token": "token-1",
//                    "userId": 149276
//        },
//            "passTemplate": {
//            "background": 2,
//                    "desc": "详情: 慕课",
//                    "end": 1529424000000,
//                    "hasToken": true,
//                    "id": 9,
//                    "limit": 9998,
//                    "start": 1527696000000,
//                    "summary": "简介: 慕课",
//                    "title": "慕课-2"
//        }
//        }
//  ],
//        "errorCode": 0,
//            "errorMsg": ""
//    }
    @Test
    public void testGetUserAllPassInfo() throws Exception {

        System.out.println(JSON.toJSONString(
                userPassService.getUserAllPassInfo(userId)
        ));
    }

    // {"errorCode":0,"errorMsg":""}
    @Test
    public void testUserUsePass() {

        Pass pass = new Pass();
        pass.setUserId(userId);
        pass.setTemplateId("f8e97f46b58f54edfcdbfe59258ca437");

        System.out.println(JSON.toJSONString(
                userPassService.userUsePass(pass)
        ));
    }
}
