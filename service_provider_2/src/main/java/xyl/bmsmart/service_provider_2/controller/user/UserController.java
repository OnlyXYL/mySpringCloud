package xyl.bmsmart.service_provider_2.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.service_provider_2.model.User;
import xyl.bmsmart.service_provider_2.service.IUserService;
import xyl.bmsmart.service_provider_2.util.RedisCacheUtil;

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

    @Resource
    private RedisCacheUtil redisCacheUtil;

    /**
     * 存在问题，由于既包含注解的方式，又包含传统的缓存方式，因此需要能够获取注解时的key
     *
     * @param param
     * @return
     */
    @Cacheable(cacheNames = "user")
    @RequestMapping(value = "/{param}")
    public String getUserById(@PathVariable("param") String param) {
        log.info("\n:::调用注解方式和的mybatis 执行查询\n");
        User user = userService.findUserById(param);

        redisCacheUtil.setCacheObject("springCloud", "springCloud");

        String springCloud = redisCacheUtil.getCacheObject("springCloud");

        log.info("springCloud:" + springCloud);

        return user.toString();
    }

    //    @CachePut(cacheNames = "user", key = "#userId")
    @CacheEvict(cacheNames = "user", allEntries = true)
    @GetMapping(value = "/{userId}/{name}")
    public String updateUser(@PathVariable("name") String name, @PathVariable("userId") String userId) {
        log.info("\n:::调用注解方式和的mybatis 执行更新\n");
        //查询用户
        User user = userService.findUserById(userId);

        log.info("\n 查询结果 " + user + "\n");

        user.setName(name);

        //更新用户
        log.info("\n 执行更新\n");
        int updateUser = userService.updateUser(user);

        log.info("\n 更新结果 " + updateUser + "\n");

        User user1 = userService.findUserById(userId);

        log.info("\n 更新之后执行查询结果 " + user1 + "\n");

        return user1.toString();
    }
}
