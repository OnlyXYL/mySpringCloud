package top.wikl.service.kafka.consumer.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * kafka消费者通道
 *
 * @param
 * @author XiaYaLing
 * @date 2018/6/1
 * @return
 */
public interface ConsumerChannel {

    String INPUT_CHANNEL = "inputChannel";

    @Input(INPUT_CHANNEL)
    SubscribableChannel inputChannel();
}
