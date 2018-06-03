package xyl.bmsmart.service.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import xyl.bmsmart.service.kafka.producer.channel.ProducerChannel;

@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(ProducerChannel.class)
public class KafkaProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }
}
