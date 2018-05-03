package xyl.bmsmart.service_neo4j.service_neo4j.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.common.common.model.neo4j.ResultData;
import xyl.bmsmart.service_neo4j.service_neo4j.service.neo4j.CityAndTypeService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class neo4jController {

    @Resource
    CityAndTypeService cityAndTypeService;

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

}
