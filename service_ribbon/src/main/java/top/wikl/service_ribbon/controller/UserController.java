package top.wikl.service_ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.wikl.service_ribbon.service.UserService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserController {

    @Resource
    UserService userService;

    @Resource
    RestTemplate restTemplate;

    @RequestMapping(value = "/user/{param}")
    @HystrixCommand(/*ignoreExceptions = {CIBaseException.class}, */fallbackMethod = "someThingError")
    public String getUser(@PathVariable("param") String param) {

        return restTemplate.getForObject("http://service-8762/user/{param}", String.class, param);
    }

    public String someThingError(String param, Throwable throwable) {

        Throwable cause = throwable.getCause();
        String message = throwable.getMessage();
        log.info("获取服务中抛出的异常:" + message);

        return message;
    }
}
