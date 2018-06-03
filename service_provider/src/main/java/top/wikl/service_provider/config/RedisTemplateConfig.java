package top.wikl.service_provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * redis 操作模板
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/23
 * @return
 */
@Configuration
public class RedisTemplateConfig {

    @Resource(name = "modelConnectionFactorySingle")
    private JedisConnectionFactory modelConnectionFactorySingle;

    @Resource(name = "modelConnectionFactoryCluster")
    private JedisConnectionFactory modelConnectionFactoryCluster;

    @Resource(name = "sessionConnectionFactorySingle")
    private JedisConnectionFactory sessionConnectionFactorySingle;

    @Resource(name = "sessionConnectionFactoryCluster")
    private JedisConnectionFactory sessionConnectionFactoryCluster;

    private StringRedisSerializer stringRedisSerializer;

    private JdkSerializationRedisSerializer jdkSerializationRedisSerializer;

    RedisTemplateConfig()

    {
        stringRedisSerializer = new StringRedisSerializer();
        jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
    }

    /**
     * 单节点 redis操作 Model 模板
     *
     * @param
     * @return org.springframework.data.redis.core.RedisTemplate
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "redisModelTemplateSingle")
    public RedisTemplate redisModelTemplateSingle() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(modelConnectionFactorySingle);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    /**
     * 集群 redis操作 Model 模板
     *
     * @param
     * @return org.springframework.data.redis.core.RedisTemplate
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "redisModelTemplateCluster")
    public RedisTemplate redisModelTemplateCluster() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(modelConnectionFactoryCluster);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    /**
     * 单节点 redis操作 Session 模板
     *
     * @param
     * @return org.springframework.data.redis.core.RedisTemplate
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "redisSessionTemplateSingle")
    public RedisTemplate redisSessionTemplateSingle() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(sessionConnectionFactorySingle);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    /**
     * 集群 redis操作 Session 模板
     *
     * @param
     * @return org.springframework.data.redis.core.RedisTemplate
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "redisSessionTemplateCluster")
    public RedisTemplate redisSessionTemplateCluster() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(sessionConnectionFactoryCluster);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }
}
