package xyl.bmsmart.service_neo4j.service_neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 这里需要过滤掉springBoot对neo4j的默认配置，因为关系需要动态增加，所以我们的neo4j用的是原始的driver方式，springBoot的自动配置里，会找sessionFactory,如果本地有就不会创建，没有的话，会自动创建一个，而在创建sessionFactory的时候，* 会创建一个driver，而我们项目中自己创建的已经有driver了，springBoot自动创建时，没有指定用户名密码，因此会报错。
 * actuator/health查看节点状态的时候，health的自动创建Neo4jHealthIndicatorAutoConfiguration在Neo4jDataAutoConfiguration之后，所以，在没有过滤springBoot对neo4j的自动配置时，在查看health时，由于新建一个driver,无法认证而报错
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration(exclude = {Neo4jRepositoriesAutoConfiguration.class, Neo4jDataAutoConfiguration.class})
public class ServiceDgraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDgraphApplication.class, args);
    }
}
