package xyl.bmsmart.web.web.fallback;

import org.springframework.stereotype.Component;
import xyl.bmsmart.web.web.service.FeignNeo4jService;

@Component
public class FeignNeo4jServiceFallback implements FeignNeo4jService {
    @Override
    public String getMovieInfoByTitle(String title) {
        return null;
    }

    @Override
    public String getMovieInfoByName(String name) {
        return null;
    }

    @Override
    public String queryNodeById(String label, String id) {
        return null;
    }
}
