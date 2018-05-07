package xyl.bmsmart.service_ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.core.env.Environment;

@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

    /**
     * 测试是否从远程获取配置文件
     *
     * @param env
     */
    @Autowired
    void setEnviroment(Environment env) {
        String property = env.getProperty("service-ribbon.cloud");
        System.out.println("service-ribbon.cloud from env: "
                + env.getProperty("service-ribbon.cloud"));
        System.out.println("property:" + property);
    }
}
