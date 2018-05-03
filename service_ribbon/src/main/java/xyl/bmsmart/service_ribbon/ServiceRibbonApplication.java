package xyl.bmsmart.service_ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = {Neo4jRepositoriesAutoConfiguration.class, Neo4jDataAutoConfiguration.class})
@SpringBootApplication
public class ServiceRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRibbonApplication.class, args);
	}
}
