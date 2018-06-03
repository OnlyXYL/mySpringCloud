package top.wikl.service.kafka.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.wikl.service.kafka.producer.channel.ProducerChannel;

import javax.annotation.Resource;

@Slf4j
@RestController
public class KafkaProducerController {

    @Resource
    private ProducerChannel producerChannel;

    @GetMapping(value = "/kafka/send/{message}")
    public String sendMessage(@PathVariable("message") String message) {

        log.info("message:" + message);

        Message<String> build = MessageBuilder.withPayload("message:" + message).build();

        producerChannel.outputChannel().send(build);

        return message;

    }
}
