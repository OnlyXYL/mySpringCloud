package xyl.bmsmart.service_feign.service_feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyl.bmsmart.service_feign.service_feign.service.CallUserService;

@Component
@Slf4j
public class FeignCallUserServiceFallback implements CallUserService {
    @Override
    public String getUser(String param) {
        log.info("+++++++FeignCallUserServiceFallback++++++++++");
        return "something wrong in getting user by param";
    }
}
