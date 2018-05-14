package xyl.bmsmart.service_provider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * 模型和session单节点连接工厂
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/23
 * @return
 */
@Configuration
@PropertySource({"classpath:redis_model.properties", "classpath:redis_session.properties"})
public class JedisConnectionFactoryConfig {

    @Value("${redis.model.host}")
    private String modelHostName;

    @Value("${redis.model.port}")
    private int modelPort;

    @Value("${redis.session.host}")
    private String sessionHostName;

    @Value("${redis.session.port}")
    private int sessionPort;

    @Resource
    private JedisPoolConfig jedisPoolConfig;

    @Resource(name = "modelRedisClusterConfiguration")
    private RedisClusterConfiguration modelRedisClusterConfiguration;

    @Resource(name = "sessionRedisClusterConfiguration")
    private RedisClusterConfiguration sessionRedisClusterConfiguration;

  /*  @Value("${redis.model.password}")
    private String modelPassword;*/

   /* @Value("${redis.session.password}")
    private String sessionPassword;*/

    /**
     * 模型单节点连接工厂
     *
     * @param
     * @return org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "modelConnectionFactorySingle")
    public JedisConnectionFactory modelConnectionFactorySingle() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(modelHostName, modelPort);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        return jedisConnectionFactory;
    }

    /**
     * 模型集群连接工厂
     *
     * @param
     * @return org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "modelConnectionFactoryCluster")
    public JedisConnectionFactory modelConnectionFactoryCluster() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(modelRedisClusterConfiguration, jedisPoolConfig);
//        jedisConnectionFactory.setTimeout(timeout);
//        jedisConnectionFactory.setPassword(modelPassword);
        return jedisConnectionFactory;
    }


    /**
     * session 单节点连接工厂
     *
     * @param
     * @return org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "sessionConnectionFactorySingle")
    public JedisConnectionFactory sessionConnectionFactorySingle() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(sessionHostName, sessionPort);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        return jedisConnectionFactory;
    }


    /**
     * session 集群连接工厂
     *
     * @param
     * @return org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "sessionConnectionFactoryCluster")
    public JedisConnectionFactory sessionConnectionFactoryCluster() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(sessionRedisClusterConfiguration, jedisPoolConfig);
//        jedisConnectionFactory.setTimeout(timeout);
//        jedisConnectionFactory.setPassword(sessionPassword);
        return jedisConnectionFactory;
    }
}