package xyl.bmsmart.service_neo4j.service_neo4j.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.common.common.model.neo4j.ResultData;
import xyl.bmsmart.common.common.myenum.LabelType;
import xyl.bmsmart.service_neo4j.service_neo4j.service.neo4j.CityAndTypeService;
import xyl.bmsmart.service_neo4j.service_neo4j.service.neo4j.MovieAndPersonService;

import javax.annotation.Resource;

/**
 * neo4j原始服务
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/26
 * @return
 */
@RestController
@Slf4j
public class neo4jController {

    @Resource
    CityAndTypeService cityAndTypeService;

    @Resource
    MovieAndPersonService movieAndPersonService;

    /**
     * 根据城市分类名，查询城市分类信息
     *
     * @param
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/4/26
     */
    @RequestMapping(value = "/allData", method = RequestMethod.GET)
    public String getAllData() {

        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::参数:::");

        ResultData resultData = cityAndTypeService.queryAllData("City", "", false, "1");

        System.out.println(resultData);

        return resultData.toString();
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
    public String getMovieInfoByTitle(@PathVariable("title") String title) {
        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::参数:::title:" + title);

//        title = "When Harry Met Sally";

        ResultData resultData = movieAndPersonService.queryDepthOneByTitle(title);

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
    public String getMovieInfoByName(@PathVariable("name") String name) {
        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::参数:::name:" + name);
        ResultData resultData = movieAndPersonService.queryDepthOneByName(name);

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
    public String queryNodeById(@PathVariable("label") String label, @PathVariable("id") String id) {
        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::参数:::id:" + id + ",label:" + label);
        ResultData resultData = movieAndPersonService.queryDepthOneById(label, id);
        log.info("::resultData:::" + resultData);

        return resultData.toString();
    }

}
