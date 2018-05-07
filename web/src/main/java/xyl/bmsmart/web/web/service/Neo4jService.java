package xyl.bmsmart.web.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class Neo4jService {

    @Resource
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "someThingError")
    public String getNeo4jData(String token) {

        String url = "";
        if (!StringUtils.isEmpty(token)) {
            url = url + "http://service-zuul/ribbon/allData" + "?token=" + token;
        } else {
            url = url + "http://service-zuul/ribbon/allData";
        }

        return restTemplate.getForObject(url, String.class);
    }

    public String someThingError() {
        log.info("something wrong!!!");
        return "something wrong in web!!!";
    }

}
