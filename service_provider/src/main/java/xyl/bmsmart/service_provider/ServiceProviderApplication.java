package xyl.bmsmart.service_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @EnableDiscoveryClient和@EnableEurekaClient都能使服务被注册中心eureka发现 spring cloud中discovery service有许多种实现（eureka、consul、zookeeper等等），@EnableDiscoveryClient基于spring-cloud-commons, @EnableEurekaClient基于spring-cloud-netflix。
 * 注意：使用eureka 作为注册中心时，可以使用@EnableEurekaClient和@EnableEurekaClient  使用其他注册中心的话，只能使用@EnableDiscoveryClient，换句话说就是必须依赖eureka才能使用@EnableEurekaClient
 * 我选用@EnableDiscoveryClient  因为不知道以后会不会使用其他的服务发现方式
 */
//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude = {RedisAutoConfiguration.class,RedisReactiveAutoConfiguration.class,RedisRepositoriesAutoConfiguration.class})
public class ServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderApplication.class, args);
    }
}
