package top.wikl.service_neo4j.config;

import lombok.Data;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "system", ignoreUnknownFields = false)
@PropertySource(value = "classpath:system.properties", encoding = "utf-8")
@Data
@Configuration
public class Neo4jConfig {

    private String neo4jUrl;
    private String neo4jUserName;
    private String neo4jPassword;

    @Bean
    public Session session() {
        Driver driver = GraphDatabase.driver(neo4jUrl, AuthTokens.basic(neo4jUserName, neo4jPassword));
        return driver.session();
    }
}
