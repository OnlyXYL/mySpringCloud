package xyl.bmsmart.service_feign.service_feign.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyl.bmsmart.service_feign.service_feign.service.CallServiceHiService;

import javax.annotation.Resource;

/**
 * feign方式调用服务
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/2
 * @return
 */
@RestController
@Slf4j
public class FeignController {

    @Resource
    CallServiceHiService callServiceHiService;

    @RequestMapping(value = "/hi/{param}", method = RequestMethod.GET)
    public String sayHi(@PathVariable String param) {
        log.info("################ call by feign ###################");
        return callServiceHiService.home(param);
    }

    @RequestMapping(value = "/feign/{param}", method = RequestMethod.GET)
    public String testFeign(@PathVariable String param) {
        return callServiceHiService.testFeignHystrix(param);
    }

}
