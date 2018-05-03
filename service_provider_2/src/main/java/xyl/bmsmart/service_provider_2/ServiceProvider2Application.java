package xyl.bmsmart.service_provider_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProvider2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProvider2Application.class, args);
	}
}
