package xyl.bmsmart.web.web.fallback.user;

import org.springframework.stereotype.Component;
import xyl.bmsmart.web.web.service.user.FeignUserService;

@Component
public class FeignUserServiceFallback implements FeignUserService {
    @Override
    public String getUserByParm(String param) {
        return "something wrong in web ,when calling userService in feign";
    }
}
