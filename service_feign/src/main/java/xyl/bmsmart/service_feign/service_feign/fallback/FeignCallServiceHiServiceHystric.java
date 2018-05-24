package xyl.bmsmart.service_feign.service_feign.fallback;

import org.springframework.stereotype.Component;
import xyl.bmsmart.service_feign.service_feign.service.CallServiceHiService;

/**
 * 调用服务的方法进行降级处理
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/24
 * @return
 */
@Component
public class FeignCallServiceHiServiceHystric implements CallServiceHiService {
    @Override
    public String home(String name) {
        return "sorry,someThing wrong!" + name;
    }

    @Override
    public String testFeignHystrix(String param) {
        return "feign wrong" + param;
    }
}
