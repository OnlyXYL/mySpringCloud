package top.wikl.service_ribbon.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.wikl.service_ribbon.service.Neo4jService;

import javax.annotation.Resource;

/**
 * neo4j service
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/2
 * @return
 */

@Service
@Slf4j
public class Neo4JServiceImpl implements Neo4jService {

    @Resource
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "someThingError")
    @Override
    public String getNeo4jData() {
        return restTemplate.getForObject("http://service-neo4j/allData/", String.class);
    }

    public String someThingError() {
        log.info("something wrong in ribbon!!!");
        return "something wrong in ribbon!!!";
    }
}
