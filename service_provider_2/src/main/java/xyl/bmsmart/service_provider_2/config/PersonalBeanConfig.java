package xyl.bmsmart.service_provider_2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import xyl.bmsmart.service_provider_2.util.RedisCacheUtil;

import javax.annotation.Resource;

@Configuration
@PropertySource({"classpath:redis.properties"})
public class PersonalBeanConfig {

    @Value("${redis.expireTime}")
    private long modelExpireTime;

    @Value("${redis.openCache}")
    private boolean openCache;

    @Value("${redis.openCluster}")
    private boolean openCluster;

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 操作 model 的redis服务
     *
     * @param
     * @return com.bmsmart.spring.boot.springboot.service.ModelRedisCacheService
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean
    public RedisCacheUtil redisCacheUtil() {
        RedisCacheUtil redisCacheUtil = new RedisCacheUtil();
        redisCacheUtil.setExpireTime(modelExpireTime);
        redisCacheUtil.setOpenCache(openCache);
        redisCacheUtil.setRedisTemplate(redisTemplate);
        return redisCacheUtil;
    }

}
