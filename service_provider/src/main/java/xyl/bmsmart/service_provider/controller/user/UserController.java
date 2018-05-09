package xyl.bmsmart.service_provider.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.common.common.model.SCUser;
import xyl.bmsmart.service_provider.controller.BaseController;
import xyl.bmsmart.service_provider.exception.BusinessException;
import xyl.bmsmart.service_provider.service.user.UserService;
import xyl.bmsmart.service_provider.util.MessageUtil;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    UserService userService;

    /**
     * 根据条件查询用户信息
     *
     * @param param
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/5/9
     */
    @RequestMapping(value = "/{param}")
    public String getUser(@PathVariable("param") String param) {

        log.info("\n:::调用xml方式和的mybatis\n");
        //判断参数是否为空
        if (StringUtils.isEmpty(param)) {
            throw new BusinessException(MessageUtil.getProperty("businessErrorMsg"));//业务异常
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("userId", param);
            SCUser user = userService.getUser(map);
            return user.toString();
        }
    }
}
