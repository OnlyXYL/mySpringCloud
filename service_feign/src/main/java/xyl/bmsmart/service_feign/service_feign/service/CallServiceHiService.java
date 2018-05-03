package xyl.bmsmart.service_feign.service_feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyl.bmsmart.service_feign.service_feign.fallback.FeignCallServiceHiServiceHystric;

/**
 * feign方式调用服务时，@FeignClient中指定服务名和Controller请求路径
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/2
 * @return
 */
@FeignClient(value = "service-8762", fallback = FeignCallServiceHiServiceHystric.class)
public interface CallServiceHiService {

    @RequestMapping("/hi/{param}")
    String home(@PathVariable("param") String name);

    @RequestMapping("/feign/{param}")
    String testFeignHystrix(@PathVariable("param") String param);

}
