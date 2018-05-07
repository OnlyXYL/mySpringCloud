package xyl.bmsmart.service_ribbon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.service_ribbon.service.HiRibbonService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HiRibbonController {

    @Resource
    HiRibbonService hiRibbonService;

//    @Value("${cloud}")
    String cloud;

    @RequestMapping(value = "/hi/{param}")
    public String hi(@PathVariable("param") String name) {

        String result = hiRibbonService.hiRibbonService(name);

        return result;
    }


    /**
     * 从server-config 中获取配置文件
     *
     * @param
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/4/28
     */
    @RequestMapping(value = "/cloud")
    public String getConfigFromConfigServer() {
        return "get cloud from server-config," + cloud;
    }

}
