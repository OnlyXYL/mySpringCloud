package top.wikl.service_feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.wikl.service_feign.fallback.FeignCallUserServiceFallback;

/**
 * feign方式调用用户服务
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/9
 * @return
 */
@FeignClient(value = "service-8762", fallback = FeignCallUserServiceFallback.class)
public interface CallUserService {
    @RequestMapping(value = "/user/{param}")
    String getUser(@PathVariable("param") String param);
}
