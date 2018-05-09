package xyl.bmsmart.service_provider_2.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.service_provider_2.model.User;
import xyl.bmsmart.service_provider_2.service.IUserService;

import javax.annotation.Resource;

/**
 * 用户微服务 controller
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/9
 * @return
 */
@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/{param}")
    public String getUserById(@PathVariable("param") String param) {
        log.info("\n:::调用注解方式和的mybatis\n");
        User user = userService.findUserById(param);

        return user.toString();
    }
}
