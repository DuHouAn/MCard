package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;
import com.southeast.passbook.vo.CreateMerchantsRequest;
import com.southeast.passbook.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <h1>商品服务测试类</h1>
 * Created by 18351 on 2019/5/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServTest {
    @Autowired
    private IMerchantsServ merchantsServ;

    @Test
    // @Transactional 可识别该测试用例，不会在数据层面发生变化
    public void testCreateMerchantsServ(){
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("慕课");
        request.setLogoUrl("www.imooc.com");
        request.setBusinessLicenseUrl("www.imooc.com");
        request.setPhone("1234567890");
        request.setAddress("北京市");

        //使用 merchantsServ.createMerchants(request)
        System.out.println(JSON.toJSONString(merchantsServ.createMerchants(request)));
    }

    @Test
    public void testBuildMerchantsInfoById(){
        //System.out.println(JSON.toJSONString(merchantsServ.buildMerchantsInfoById(2)));
        /**
         * 输出结果：
         * {
         * "data":{"address":"北京市","businessLicenseUrl":"www.imooc.com","id":2,"isAudit":false,"logoUrl":"www.imooc.com","name":"慕课","phone":"1234567890"},
         * "errorCode":0,"
         * errorMsg":""
         * }
         */
        System.out.println(JSON.toJSONString(merchantsServ.buildMerchantsInfoById(1)));
        /**
         * 输出结果：
         * {
         * "errorCode":6,
         * "errorMsg":"商户不存在"
         * }
         */
    }

    /**
     *  Drop Template{"background":2,"desc":"详情: 慕课","end":1558408575162,"
     *  hasToken":false,"id":2,"limit":10000,"start":1556680575160,"summary":"简介: 慕课","title":"慕课-1"}
     */
    @Test
    public void testDropPassTemplate(){
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(2);
        passTemplate.setTitle("慕课-1");
        passTemplate.setSummary("简介: 慕课");
        passTemplate.setDesc("详情: 慕课");
        passTemplate.setLimit(10000L);
        passTemplate.setHasToken(false);
        passTemplate.setBackground(2);
        passTemplate.setStart(DateUtils.addDays(new Date(), -10));
        passTemplate.setEnd(DateUtils.addDays(new Date(), 10));

        System.out.println(JSON.toJSONString(
                merchantsServ.dropPassTemplate(passTemplate)
        ));
    }
}
