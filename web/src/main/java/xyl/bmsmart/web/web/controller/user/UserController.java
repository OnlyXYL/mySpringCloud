package xyl.bmsmart.web.web.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.web.web.service.user.FeignUserService;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    FeignUserService feignUserService;

    @RequestMapping(value = "/{param}")
    public String getUser(@PathVariable("param") String param) {

        String userByParm = feignUserService.getUserByParm(param);
        return userByParm;
    }
}
