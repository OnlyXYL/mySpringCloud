package top.wikl.service_ribbon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wikl.service_ribbon.service.Neo4jService;

import javax.annotation.Resource;

@RestController
public class Neo4jController {

    @Resource
    Neo4jService neo4jService;

    @RequestMapping(value = "/allData")
    public String getNeo4jData() {
        String neo4jData = neo4jService.getNeo4jData();
        return neo4jData;
    }
}
