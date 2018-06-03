package top.wikl.web.service.user;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.wikl.web.fallback.user.FeignUserServiceFallback;

@FeignClient(value = "service-zuul/feign", fallback = FeignUserServiceFallback.class)
public interface FeignUserService {

    @RequestMapping(value = "/user/{param}")
    String getUserByParm(@PathVariable("param") String param);
}
