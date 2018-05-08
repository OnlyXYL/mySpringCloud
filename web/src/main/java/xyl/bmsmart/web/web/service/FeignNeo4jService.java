package xyl.bmsmart.web.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyl.bmsmart.web.web.fallback.FeignNeo4jServiceFallback;

@FeignClient(value = "service-zuul/feign", fallback = FeignNeo4jServiceFallback.class)
public interface FeignNeo4jService {

    @RequestMapping(value = "/movie/{title}")
    String getMovieInfoByTitle(@PathVariable("title") String title);

    @RequestMapping(value = "/person/{name}")
    String getMovieInfoByName(@PathVariable("name") String name);

    @RequestMapping(value = "/node/{label}/{id}")
    String queryNodeById(@PathVariable("label") String label, @PathVariable("id") String id);
}
