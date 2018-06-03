package top.wikl.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.wikl.web.handler.CustomerErrorDecoder;

@Configuration
public class PersonalBeanConfig {

    @Bean
    public CustomerErrorDecoder customerErrorDecoder(){
        CustomerErrorDecoder customerErrorDecoder = new CustomerErrorDecoder();
        return customerErrorDecoder;
    }
}
