package top.wikl.service_provider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@EnableCaching//启动缓存
@PropertySource({"classpath:redis_model.properties", "classpath:redis_session.properties"})
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    @Resource(name = "modelConnectionFactorySingle")
    private JedisConnectionFactory modelConnectionFactorySingle;

    @Resource(name = "modelConnectionFactoryCluster")
    private JedisConnectionFactory modelConnectionFactoryCluster;

    @Resource(name = "sessionConnectionFactorySingle")
    private JedisConnectionFactory sessionConnectionFactorySingle;

    @Resource(name = "sessionConnectionFactoryCluster")
    private JedisConnectionFactory sessionConnectionFactoryCluster;

    @Value("${redis.model.expireTime}")
    private long modelExpireTime;

    @Value("${redis.session.expireTime}")
    private long sessionExpireTime;


    /**
     * 获取redisCacheManager
     *
     * @param time                   过期时间
     * @param redisConnectionFactory 连接工厂
     * @return org.springframework.data.redis.cache.RedisCacheManager
     * @author XiaYaLing
     * @date 2018/5/14
     */
    public RedisCacheManager getRedisCacheManager(long time, RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(time));
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration);
        RedisCacheManager modelClusterRedisCacheManager = builder.build();
        return modelClusterRedisCacheManager;
    }

    /**
     * 单节点模型缓存管理类
     *
     * @param
     * @return org.springframework.cache.CacheManager
     * @author XiaYaLing
     * @date 2018/5/10
     */
    @Bean(name = "modelSingleRedisCacheManager")
    public CacheManager modelSingleRedisCacheManager() {
        log.info("\n初始化 CacheManager...\n");
        /**
         * 调用方法获取redisCacheManager
         */
        RedisCacheManager modelSingleRedisCacheManager = this.getRedisCacheManager(modelExpireTime, modelConnectionFactorySingle);
//        RedisCacheManager modelSingleRedisCacheManager = RedisCacheManager.create(modelConnectionFactorySingle);
        return modelSingleRedisCacheManager;
    }

    /**
     * 集群模型缓存管理类
     *
     * @param
     * @return org.springframework.cache.CacheManager
     * @author XiaYaLing
     * @date 2018/5/10
     */
    @Primary
    @Bean(name = "modelClusterRedisCacheManager")
    public CacheManager modelClusterRedisCacheManager() {
        log.info("\n初始化 CacheManager...\n");

        /**
         * 调用方法获取redisCacheManager
         */
        RedisCacheManager modelClusterRedisCacheManager = this.getRedisCacheManager(modelExpireTime, modelConnectionFactoryCluster);

        return modelClusterRedisCacheManager;
    }

    /**
     * 单节点Session缓存管理类
     *
     * @param
     * @return org.springframework.cache.CacheManager
     * @author XiaYaLing
     * @date 2018/5/10
     */
    @Bean(name = "sessionSingleRedisCacheManager")
    public CacheManager sessionSingleRedisCacheManager() {
        log.info("\n初始化 CacheManager...\n");
//        RedisCacheManager sessionSingleRedisCacheManager = RedisCacheManager.create(sessionConnectionFactorySingle);
        /**
         * 调用方法获取redisCacheManager
         */
        RedisCacheManager sessionSingleRedisCacheManager = this.getRedisCacheManager(sessionExpireTime, sessionConnectionFactorySingle);
        return sessionSingleRedisCacheManager;
    }

    /**
     * 集群Session缓存管理类
     *
     * @param
     * @return org.springframework.cache.CacheManager
     * @author XiaYaLing
     * @date 2018/5/10
     */
    @Bean(name = "sessionClusterRedisCacheManager")
    public CacheManager sessionClusterRedisCacheManager() {
        log.info("\n初始化 CacheManager...\n");
        /**
         * 调用方法获取redisCacheManager
         */
        RedisCacheManager sessionClusterRedisCacheManager = this.getRedisCacheManager(sessionExpireTime, sessionConnectionFactoryCluster);
//        RedisCacheManager sessionClusterRedisCacheManager = RedisCacheManager.create(sessionConnectionFactoryCluster);
        return sessionClusterRedisCacheManager;
    }

    /**
     * 生成key的策略(单节点Session)
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     *
     * @return
     */
    @Bean(name = "singleSessionKeyGenerator")
    public KeyGenerator singleSessionKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append("single_Session_" + target.getClass().getName());
                sb.append("_" + method.getName());
                for (Object obj : params) {
                    sb.append("_" + obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 生成key的策略(集群Session)
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     *
     * @return
     */
    @Bean(name = "clusterSessionKeyGenerator")
    public KeyGenerator clusterSessionKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append("cluster_Session_" + target.getClass().getName());
                sb.append("_" + method.getName());
                for (Object obj : params) {
                    sb.append("_" + obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 生成key的策略(单节点模型)
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     *
     * @return
     */
    @Bean(name = "singleModelKeyGenerator")
    public KeyGenerator singleModelKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append("sigle_Model_" + target.getClass().getName());
                sb.append("_" + method.getName());
                for (Object obj : params) {
                    sb.append("_" + obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 生成key的策略(集群模型)
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     *
     * @return
     */
    @Bean(name = "clusterModelKeyGenerator")
    public KeyGenerator clusterModelKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append("cluster_Model_" + target.getClass().getName());
                sb.append("_" + method.getName());
                for (Object obj : params) {
                    sb.append("_" + obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
