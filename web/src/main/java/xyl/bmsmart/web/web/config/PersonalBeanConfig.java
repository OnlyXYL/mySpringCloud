package xyl.bmsmart.web.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyl.bmsmart.web.web.handler.CustomerErrorDecoder;

@Configuration
public class PersonalBeanConfig {

    @Bean
    public CustomerErrorDecoder customerErrorDecoder(){
        CustomerErrorDecoder customerErrorDecoder = new CustomerErrorDecoder();
        return customerErrorDecoder;
    }
}
