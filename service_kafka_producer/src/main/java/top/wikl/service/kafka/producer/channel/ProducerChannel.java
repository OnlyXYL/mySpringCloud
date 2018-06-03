package top.wikl.service.kafka.producer.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * kafka生产者通道
 *
 * @param
 * @author XiaYaLing
 * @date 2018/6/1
 * @return
 */
public interface ProducerChannel {

    //发送队列
    String OUTPUT_CHANNEL = "outputChannel";

    @Output(OUTPUT_CHANNEL)
    MessageChannel outputChannel();
}
