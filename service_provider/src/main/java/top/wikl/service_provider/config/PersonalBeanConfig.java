package top.wikl.service_provider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import top.wikl.service_provider.service.redis.ModelRedisCacheService;
import top.wikl.service_provider.service.redis.SessionRedisCacheService;

@Configuration
@PropertySource({"classpath:redis_model.properties", "classpath:redis_common.properties", "classpath:redis_session.properties"})
public class PersonalBeanConfig {

    @Value("${redis.model.expireTime}")
    private long modelExpireTime;

    @Value("${redis.openCache}")
    private boolean openCache;

    @Value("${redis.session.expireTime}")
    private long sessionExpireTime;


    /**
     *    操作 model 的redis服务
     * @author XiaYaLing
     * @date 2018/4/23
     * @param
     * @return com.bmsmart.spring.boot.springboot.service.ModelRedisCacheService
     */
    @Bean
    public ModelRedisCacheService modelRedisCacheService(){
        ModelRedisCacheService modelRedisCacheService = new ModelRedisCacheService();
        modelRedisCacheService.setExpireTime(modelExpireTime);
        modelRedisCacheService.setOpenCache(openCache);
        return modelRedisCacheService;
    }

    /**
     *    操作 session 的redis服务
     * @author XiaYaLing
     * @date 2018/4/23
     * @param
     * @return com.bmsmart.spring.boot.springboot.service.SessionRedisCacheService
     */
    @Bean
    public SessionRedisCacheService sessionRedisCacheService(){
        SessionRedisCacheService sessionRedisCacheService = new SessionRedisCacheService();
        sessionRedisCacheService.setExpireTime(sessionExpireTime);
        sessionRedisCacheService.setOpenCache(openCache);
        return sessionRedisCacheService;
    }


}
