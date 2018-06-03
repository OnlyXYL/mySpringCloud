package top.wikl.service_ribbon.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import top.wikl.common.exception.CIBaseException;
import top.wikl.service_ribbon.service.UserService;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "someThingError", ignoreExceptions = CIBaseException.class)
    @Override
    public String getUser(@PathVariable("param") String param) {

        return restTemplate.getForObject("http://service-8762/user/{param}", String.class);
    }

    public String someThingError(String param, Throwable throwable) {

        Throwable cause = throwable.getCause();
        String message = throwable.getMessage();
        log.info("获取服务中抛出的异常:" + message);

        return message;
    }
}
