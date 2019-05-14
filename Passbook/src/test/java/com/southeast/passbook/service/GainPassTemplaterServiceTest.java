package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;

import com.southeast.passbook.service.IGainPassTemplateService;
import com.southeast.passbook.vo.GainPassTemplateRequest;
import com.southeast.passbook.vo.PassTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户领取优惠券功能测试</h1>
 * Created by 18351 on 2019/5/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GainPassTemplaterServiceTest extends AbstractServiceTest {

    @Autowired
    private IGainPassTemplateService gainPassTemplateService;

    @Test
    public void testGainPassTemplate() throws Exception {

        PassTemplate target = new PassTemplate();
        target.setId(2);
        target.setTitle("慕课-1");
        target.setHasToken(true);

        System.out.println(JSON.toJSONString(
                gainPassTemplateService.gainPassTemplate(
                        new GainPassTemplateRequest(userId, target)
                )
        ));
    }
}
