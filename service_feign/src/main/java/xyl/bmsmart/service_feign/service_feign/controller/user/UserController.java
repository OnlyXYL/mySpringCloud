package xyl.bmsmart.service_feign.service_feign.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.service_feign.service_feign.service.CallUserService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserController {

    @Resource
    CallUserService callUserService;

    @RequestMapping(value = "/user/{param}")
    public String getUserByParm(@PathVariable("param") String param) {

        String user = callUserService.getUser(param);

        return user;
    }
}
