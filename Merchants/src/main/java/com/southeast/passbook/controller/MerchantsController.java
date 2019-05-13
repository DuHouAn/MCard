package com.southeast.passbook.controller;

import com.alibaba.fastjson.JSON;
import com.southeast.passbook.service.IMerchantsServ;
import com.southeast.passbook.vo.CreateMerchantsRequest;
import com.southeast.passbook.vo.PassTemplate;
import com.southeast.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>商户服务</h1>
 * Created by 18351 on 2019/5/11.
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsController {
    private final IMerchantsServ merchantsServ; //商户服务接口

    @Autowired
    public MerchantsController(IMerchantsServ merchantsServ){
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody  CreateMerchantsRequest request){
        log.info("CreateMerchants:{}", JSON.toJSONString(request));
        return merchantsServ.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BuildMerchantsInfo:{}", id);
        return merchantsServ.buildMerchantsInfoById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate){
        log.info("DropPassTemplate:{}", passTemplate);
        return merchantsServ.dropPassTemplate(passTemplate);
    }
}
