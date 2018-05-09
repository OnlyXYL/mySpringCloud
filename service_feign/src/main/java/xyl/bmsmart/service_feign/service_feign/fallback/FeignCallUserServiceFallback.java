package xyl.bmsmart.service_feign.service_feign.fallback;

import org.springframework.stereotype.Component;
import xyl.bmsmart.service_feign.service_feign.service.CallUserService;

@Component
public class FeignCallUserServiceFallback implements CallUserService {
    @Override
    public String getUser(String param) {
        return "something wrong in getting user by param";
    }
}
