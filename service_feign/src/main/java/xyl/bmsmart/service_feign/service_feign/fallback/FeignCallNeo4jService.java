package xyl.bmsmart.service_feign.service_feign.fallback;

import org.springframework.stereotype.Component;
import xyl.bmsmart.service_feign.service_feign.service.CallNeo4jService;

@Component
public class FeignCallNeo4jService implements CallNeo4jService {
    @Override
    public String getNeo4jData() {
        return null;
    }
}
