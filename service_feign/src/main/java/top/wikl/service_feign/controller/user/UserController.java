package top.wikl.service_feign.controller.user;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wikl.service_feign.service.CallUserService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserController {

    @Resource
    CallUserService callUserService;

    @RequestMapping(value = "/user/{param}")
    public String getUserByParm(@PathVariable("param") String param) {

        String user = null;
        try {
            user = callUserService.getUser(param);
        } catch (Exception e) {
            //向上抛出，feign中捕获到的服务中异常
            e.printStackTrace();
            if (e instanceof HystrixBadRequestException) {
                log.info("HystrixBadRequestException," + "message:" + e.getMessage() + "cause," + e.getCause());
                throw e;
            }
        }
        return user;
    }
}
