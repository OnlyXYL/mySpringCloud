package top.wikl.service_ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class HiRibbonService {

    @Resource
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "someThingError")
    public String hiRibbonService(String name) {
        log.info("################ call by ribbon ###################");
        return restTemplate.getForObject("http://service-8762/hi/" + name, String.class);
    }

    public String someThingError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
