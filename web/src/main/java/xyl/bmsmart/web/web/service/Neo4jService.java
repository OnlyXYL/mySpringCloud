package xyl.bmsmart.web.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import xyl.bmsmart.common.common.myenum.BalanceType;
import xyl.bmsmart.web.web.config.SystemProperties;

import javax.annotation.Resource;

@Service
@Slf4j
public class Neo4jService {

    @Resource
    RestTemplate restTemplate;

    @Resource
    SystemProperties systemProperties;

    @HystrixCommand(fallbackMethod = "someThingError")
    public String getNeo4jData(String token) {

        String url = "";

        //判断负载均衡方式
        if (BalanceType.feign.getKey().equals(systemProperties.getBalanceType())) {
            //feign方式
            if (!StringUtils.isEmpty(token)) {
                url = url + "http://service-zuul/feign/allData" + "?token=" + token;
            } else {
                url = url + "http://service-zuul/feign/allData";
            }
        } else if (BalanceType.ribbon.getKey().equals(systemProperties.getBalanceType())) {
            //ribbon方式
            if (!StringUtils.isEmpty(token)) {
                url = url + "http://service-zuul/ribbon/allData" + "?token=" + token;
            } else {
                url = url + "http://service-zuul/ribbon/allData";
            }
        }
        return restTemplate.getForObject(url, String.class);
    }

    public String someThingError(String token) {
        log.info("something wrong!!!");
        return "something wrong in web!!!";
    }

}
