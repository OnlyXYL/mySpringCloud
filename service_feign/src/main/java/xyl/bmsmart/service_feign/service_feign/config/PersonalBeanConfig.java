package xyl.bmsmart.service_feign.service_feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyl.bmsmart.service_feign.service_feign.handler.CustomerErrorDecoder;

@Configuration
public class PersonalBeanConfig {

    @Bean
    public CustomerErrorDecoder customerErrorDecoder(){
        CustomerErrorDecoder customerErrorDecoder = new CustomerErrorDecoder();
        return customerErrorDecoder;
    }
}
