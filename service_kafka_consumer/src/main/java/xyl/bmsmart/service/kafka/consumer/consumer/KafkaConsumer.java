package xyl.bmsmart.service.kafka.consumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import xyl.bmsmart.service.kafka.consumer.channel.ConsumerChannel;

@Component
@Slf4j
public class KafkaConsumer {

    @StreamListener(ConsumerChannel.INPUT_CHANNEL)
    public void getMessge(String message) {
        log.info("接收到的消息:" + message);
    }
}
