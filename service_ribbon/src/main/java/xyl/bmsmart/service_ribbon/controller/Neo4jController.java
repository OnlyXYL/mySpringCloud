package xyl.bmsmart.service_ribbon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.common.common.model.neo4j.ResultData;
import xyl.bmsmart.service_ribbon.service.Neo4jService;

import javax.annotation.Resource;

@RestController
public class Neo4jController {

    @Resource
    Neo4jService neo4jService;

    @RequestMapping(value = "/allData")
    public ResultData getNeo4jData() {
        ResultData neo4jData = neo4jService.getNeo4jData();
        return neo4jData;
    }
}
