package xyl.bmsmart.service_neo4j.service_neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceNeo4jApplication.class, args);
	}
}
