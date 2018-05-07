package xyl.bmsmart.service_feign.service_feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import xyl.bmsmart.service_feign.service_feign.fallback.FeignCallNeo4jService;

/**
 * feign方式调用neo4j服务
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/7
 * @return http://service-neo4j/allData/
 */
@FeignClient(value = "service-neo4j", fallback = FeignCallNeo4jService.class)
public interface CallNeo4jService {

    @RequestMapping("/allData")
    String getNeo4jData();
}
