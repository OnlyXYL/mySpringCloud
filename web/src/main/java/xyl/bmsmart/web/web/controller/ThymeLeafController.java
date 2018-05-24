package xyl.bmsmart.web.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyl.bmsmart.common.common.exception.ErrorPageException;
import xyl.bmsmart.web.web.config.RemoteProperties;
import xyl.bmsmart.web.web.service.FeignNeo4jService;
import xyl.bmsmart.web.web.service.Neo4jService;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/thymeleaf")
@Slf4j
public class ThymeLeafController {

    @Resource
    RemoteProperties remoteProperties;

    @Resource
    FeignNeo4jService feignNeo4jService;

    @Resource
    Neo4jService neo4jService;

    @RequestMapping(value = "/hello1")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/hello1");
        mv.addObject("title", "欢迎使用Thymeleaf!");
        return mv;
    }

    /**
     * 获取页面
     *
     * @param
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/5/7
     */
    @RequestMapping(value = "/page/neo4j")
    public String getPage() {

        return "neo4j1";
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

    /**
     * 根据电影名查询深度为1的关系图谱
     *
     * @param title
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/5/8
     */
    @RequestMapping(value = "/movie/{title}")
    @ResponseBody
    public String getMovieInfoByTitle(@PathVariable("title") String title) {

        String resultData = feignNeo4jService.getMovieInfoByTitle(title);

        log.info(":::resultData:::" + resultData);

        return resultData.toString();
    }

    /**
     * 根据人名查询深度为1的演员关系图谱
     *
     * @param name
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/5/8
     */
    @RequestMapping(value = "/person/{name}")
    @ResponseBody
    public String getMovieInfoByName(@PathVariable("name") String name) {
        String resultData = feignNeo4jService.getMovieInfoByName(name);

        log.info("::resultData:::" + resultData);

        return resultData.toString();
    }

    /**
     * 根据节点id查询深度为1的关系图谱
     *
     * @param label
     * @param id
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/5/8
     */
    @RequestMapping(value = "/node/{label}/{id}")
    @ResponseBody
    public String queryNodeById(@PathVariable("label") String label, @PathVariable("id") String id) {

        String resultData = feignNeo4jService.queryNodeById(label, id);
        log.info("::resultData:::" + resultData);

        return resultData.toString();
    }

}
