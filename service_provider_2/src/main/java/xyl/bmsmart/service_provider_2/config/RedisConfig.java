package xyl.bmsmart.service_provider_2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

@Configuration
@EnableCaching//启动缓存
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用JdkSerializationRedisSerializer来序列化和反序列化redis的value值
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 缓存管理类
     *
     * @param connectionFactory
     * @return org.springframework.cache.CacheManager
     * @author XiaYaLing
     * @date 2018/5/10
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        log.info("\n初始化 CacheManager...\n");
        RedisCacheManager redisCacheManager = RedisCacheManager.create(connectionFactory);
        return redisCacheManager;
    }

    /**
     * 生成key的策略
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("_" + method.getName());
                for (Object obj : params) {
                    sb.append("_" + obj.toString());
                }
                return sb.toString();
            }
        };
    }


}
