package top.wikl.service_provider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource({"classpath:redis_model.properties", "classpath:redis_session.properties"})
public class RedisClusterConfig {

    public List<String> modelClusterNodes = new ArrayList<>();

    public List<String> sessionClusterNodes = new ArrayList<>();
    /**
     * model.cluster1.host.port[0]=192.168.1.128:7007
     * model.cluster2.host.port[1]=192.168.1.128:7008
     * model.cluster3.host.port[2]=192.168.1.128:7009
     * model.cluster4.host.port[3]=192.168.1.129:7010
     * model.cluster5.host.port[4]=192.168.1.129:7011
     * model.cluster6.host.port[5]=192.168.1.129:7012
     *
     * @return
     */

    @Value("${model.cluster2.host.port[0]}")
    private String modelHostAndPort0;

    @Value("${model.cluster2.host.port[1]}")
    private String modelHostAndPort1;

    @Value("${model.cluster3.host.port[2]}")
    private String modelHostAndPort2;

    @Value("${model.cluster4.host.port[3]}")
    private String modelHostAndPort3;

    @Value("${model.cluster5.host.port[4]}")
    private String modelHostAndPort4;

    @Value("${model.cluster6.host.port[5]}")
    private String modelHostAndPort5;

    @Value("${session.cluster1.host.port[0]}")
    private String sessionHostAndPort0;

    @Value("${session.cluster2.host.port[1]}")
    private String sessionHostAndPort1;

    @Value("${session.cluster3.host.port[2]}")
    private String sessionHostAndPort2;

    @Value("${session.cluster4.host.port[3]}")
    private String sessionHostAndPort3;

    @Value("${session.cluster5.host.port[4]}")
    private String sessionHostAndPort4;

    @Value("${session.cluster6.host.port[5]}")
    private String sessionHostAndPort5;

    /*public RedisClusterConfig() {
        modelClusterNodes.add(modelHostAndPort0);
        modelClusterNodes.add(modelHostAndPort1);
        modelClusterNodes.add(modelHostAndPort2);
        modelClusterNodes.add(modelHostAndPort3);
        modelClusterNodes.add(modelHostAndPort4);
        modelClusterNodes.add(modelHostAndPort5);

        sessionClusterNodes.add(sessionHostAndPort0);
        sessionClusterNodes.add(sessionHostAndPort1);
        sessionClusterNodes.add(sessionHostAndPort2);
        sessionClusterNodes.add(sessionHostAndPort3);
        sessionClusterNodes.add(sessionHostAndPort4);
        sessionClusterNodes.add(sessionHostAndPort5);
    }*/

    public void settingModelRedisClusterNodes() {
        modelClusterNodes.add(modelHostAndPort0);
        modelClusterNodes.add(modelHostAndPort1);
        modelClusterNodes.add(modelHostAndPort2);
        modelClusterNodes.add(modelHostAndPort3);
        modelClusterNodes.add(modelHostAndPort4);
        modelClusterNodes.add(modelHostAndPort5);
    }

    public void settingSessionRedisClusterNodes() {
        sessionClusterNodes.add(sessionHostAndPort0);
        sessionClusterNodes.add(sessionHostAndPort1);
        sessionClusterNodes.add(sessionHostAndPort2);
        sessionClusterNodes.add(sessionHostAndPort3);
        sessionClusterNodes.add(sessionHostAndPort4);
        sessionClusterNodes.add(sessionHostAndPort5);
    }

    /**
     * 模型 redis 集群节点配置
     *
     * @param
     * @return org.springframework.data.redis.connection.RedisClusterConfiguration
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "modelRedisClusterConfiguration")
    public RedisClusterConfiguration modelRedisClusterConfiguration() {
        settingModelRedisClusterNodes();
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(modelClusterNodes);
        return redisClusterConfiguration;
    }

    /**
     * session redis 集群节点配置
     *
     * @param
     * @return org.springframework.data.redis.connection.RedisClusterConfiguration
     * @author XiaYaLing
     * @date 2018/4/23
     */
    @Bean(name = "sessionRedisClusterConfiguration")
    public RedisClusterConfiguration sessionRedisClusterConfiguration() {
        settingSessionRedisClusterNodes();
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(sessionClusterNodes);
        return redisClusterConfiguration;
    }
}
