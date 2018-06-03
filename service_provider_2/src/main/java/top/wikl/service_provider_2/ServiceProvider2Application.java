package top.wikl.service_provider_2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("xyl.bmsmart.service_provider_2.mapper")
public class ServiceProvider2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProvider2Application.class, args);
	}
}
