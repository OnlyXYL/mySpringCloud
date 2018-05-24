package xyl.bmsmart.service_feign.service_feign.fallback;

import org.springframework.stereotype.Component;
import xyl.bmsmart.service_feign.service_feign.service.CallNeo4jService;

/**
 * 调用服务的方法进行降级处理
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/24
 * @return
 */
@Component
public class FeignCallNeo4jService implements CallNeo4jService {
    @Override
    public String getNeo4jData() {
        return "sorry,someThing wrong!";
    }

    @Override
    public String getMovieInfoByTitle(String title) {
        return "sorry,someThing wrong!" + title;
    }

    @Override
    public String getMovieInfoByName(String name) {
        return "sorry,someThing wrong!" + name;
    }

    @Override
    public String queryNodeById(String label, String id) {
        return "sorry,someThing wrong!" + label + id;
    }
}
