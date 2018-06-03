package top.wikl.service_provider.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wikl.common.exception.BusinessException;
import top.wikl.common.exception.CIBaseException;
import top.wikl.common.model.SCUser;
import top.wikl.service_provider.controller.BaseController;
import top.wikl.service_provider.service.redis.SessionRedisCacheService;
import top.wikl.service_provider.service.user.UserService;
import top.wikl.service_provider.util.MessageUtil;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    UserService userService;

    @Resource
    private SessionRedisCacheService sessionRedisCacheService;

    /**
     * 根据条件查询用户信息(集群模型)
     *
     * @param param
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/5/9
     */
    @RequestMapping(value = "/{param}")
    @Cacheable(cacheNames = "user_common", cacheManager = "sessionSingleRedisCacheManager", keyGenerator = "singleSessionKeyGenerator")
    public String getUser(@PathVariable("param") String param) {

        log.info("\n:::调用xml方式和的mybatis\n");
        //判断参数是否为空
        if (StringUtils.isEmpty(param)) {
            throw new BusinessException(MessageUtil.getProperty("businessErrorMsg"));//业务异常
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("userId", param);
            SCUser user = userService.getUser(map);
            if (user == null) {
                throw new CIBaseException("用户不存在");//该用户不存在
//                throw new HystrixBadRequestException("用户不存在");//该用户不存在
            } else {
                return user.toString();
            }
        }
    }


    /**
     * 更新
     *
     * @param name
     * @param userId
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/5/10
     */
    @CacheEvict(cacheNames = "user_common", allEntries = true)
    @RequestMapping(value = "/{userId}/{name}")
    public String updateUser(@PathVariable("name") String name, @PathVariable("userId") String userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId);
        log.info("\n执行查询");

        SCUser user = userService.getUser(map);

        log.info("\n查询结果：" + user.toString());

        map.remove("userId");
        map.put("name", name);
        map.put("userId", user.getUserId());
        log.info("\n执行更新");
        int result = userService.updateUser(map);
        log.info("\n执行更新结果：" + result);

        map.clear();
        map.put("userId", user.getUserId());
        SCUser user1 = userService.getUser(map);

        log.info("\n更新之后执行查询：" + user1.toString());

        return user1.toString();
    }
}
