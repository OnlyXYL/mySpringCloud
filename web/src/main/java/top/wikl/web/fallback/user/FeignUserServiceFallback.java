package top.wikl.web.fallback.user;

import org.springframework.stereotype.Component;
import top.wikl.web.service.user.FeignUserService;

@Component
public class FeignUserServiceFallback implements FeignUserService {
    @Override
    public String getUserByParm(String param) {
        return "something wrong in web ,when calling userService in feign";
    }
}
