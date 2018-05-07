package xyl.bmsmart.web.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyl.bmsmart.web.web.config.RemoteProperties;
import xyl.bmsmart.web.web.exception.BusinessException;
import xyl.bmsmart.web.web.exception.ErrorPageException;
import xyl.bmsmart.web.web.service.Neo4jService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/thymeleaf")
@Slf4j
public class ThymeLeafController {

    @Resource
    RemoteProperties remoteProperties;

    @Resource
    Neo4jService neo4jService;

    @RequestMapping(value = "/hello1")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/hello1");
        mv.addObject("title", "欢迎使用Thymeleaf!");
        return mv;
    }

    /**
     *    获取页面
     * @author XiaYaLing
     * @date 2018/5/7
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value = "/page/neo4j")
    public String getPage(){

        return "neo4j";
    }

    @RequestMapping(value = "/{param}/{token}")
    @ResponseBody
    public String hello(@PathVariable("param") String param, @PathVariable("token") String token) {

        String neo4jData = "";
        try {
            neo4jData = neo4jService.getNeo4jData(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorPageException("something wrong !!!");
        }

        log.info("neo4jData" + neo4jData);

        return neo4jData;
    }
}
