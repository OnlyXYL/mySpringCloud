package top.wikl.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 获取系统配置信息
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/7
 * @return
 */
@ConfigurationProperties(prefix = "system")
@PropertySource(value = {"classpath:system.properties"}, encoding = "UTF-8")
@Data
@Component
public class SystemProperties {

    /**
     * 负载均衡方式
     * feign   ribbon
     */
    private String balanceType;
}
