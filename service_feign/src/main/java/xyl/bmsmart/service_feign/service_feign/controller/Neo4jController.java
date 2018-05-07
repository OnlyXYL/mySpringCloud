package xyl.bmsmart.service_feign.service_feign.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.service_feign.service_feign.service.CallNeo4jService;

import javax.annotation.Resource;

/**
 * feign方式负载均衡获取neo4j服务
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/7
 * @return
 */
@RestController
@Slf4j
public class Neo4jController {

    @Resource
    CallNeo4jService callNeo4jService;

    @RequestMapping(value = "/allData")
    public String getAllData() {
        log.info("################ call neo4j by feign ###################");
        String neo4jData = callNeo4jService.getNeo4jData();

        return neo4jData;
    }
}
