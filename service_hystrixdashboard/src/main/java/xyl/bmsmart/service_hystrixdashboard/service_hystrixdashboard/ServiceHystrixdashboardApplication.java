package xyl.bmsmart.service_hystrixdashboard.service_hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceHystrixdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHystrixdashboardApplication.class, args);
	}
}
