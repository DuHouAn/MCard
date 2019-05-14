package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;
import com.southeast.passbook.constant.Constants;
import com.southeast.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * <h1>消费 Kafka 的 PassTemplate</h1>
 * Created by 18351 on 2019/5/12.
 */
@Slf4j
@Component
public class ConsumePassTemplate {

    private final IHBasePassService passService; //pass 相关的 Hbase 服务

    @Autowired
    public ConsumePassTemplate(IHBasePassService passService) {
        this.passService = passService;
    }

    @KafkaListener(topics = {Constants.TEMPLATE_TOPIC}) // KafkaListener 标识 Kafka 消费者
    public void receive(@Payload String passTemplate,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        // @Payload Kafka 特有的注解，从 Kafka 中接收的负载数据

        log.info("Consume Receive PassTemplate: {}",passTemplate);

        PassTemplate pt; //要得到的是 PassTemplate 对象
        try{
            //将 passTemplate 字符串，序列化为对象
            pt = JSON.parseObject(passTemplate,PassTemplate.class);
        }catch (Exception e){
            log.error("Parse PassTemplate Error:{}",e.getMessage());
            return;
        }
        log.info("DropPassTemplatetToHBase:{}",passService.dropPassTemplateToHBase(pt));
    }
}
