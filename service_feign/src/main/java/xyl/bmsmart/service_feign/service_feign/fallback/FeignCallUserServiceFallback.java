package xyl.bmsmart.service_feign.service_feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyl.bmsmart.service_feign.service_feign.service.CallUserService;

/**
 * 调用用户服务的方法进行降级处理
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/24
 * @return
 */
@Component
@Slf4j
public class FeignCallUserServiceFallback implements CallUserService {
    @Override
    public String getUser(String param) {
        log.info("+++++++ The FeignCallUserServiceFallback of feign ++++++++++");
        return "something wrong in getting user by param";
    }
}
