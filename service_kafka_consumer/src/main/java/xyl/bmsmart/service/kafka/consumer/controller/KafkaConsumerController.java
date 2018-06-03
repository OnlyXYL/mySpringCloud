package xyl.bmsmart.service.kafka.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.service.kafka.consumer.consumer.KafkaConsumer;

import javax.annotation.Resource;

@RestController
@Slf4j
public class KafkaConsumerController {

    @Resource
    KafkaConsumer kafkaConsumer;

    public String getMessage() {

        return "";
    }
}
