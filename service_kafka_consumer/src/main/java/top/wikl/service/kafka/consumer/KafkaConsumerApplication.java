package top.wikl.service.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import top.wikl.service.kafka.consumer.channel.ConsumerChannel;

@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(ConsumerChannel.class)
public class KafkaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }
}
