package xyl.bmsmart.service_provider_2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 获取属性文件中的配置信息，例如，用来配置页面显示的提示信息
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/24
 * @return
 */

/**
 * @Data lombok注解
 * lombok是一个可以帮助我们简化java代码编写的工具类，尤其是简化javabean的编写，
 * 即通过采用注解的方式，消除代码中的构造方法，getter/setter等代码，使我们写的类更加简洁，
 * 当然，这带来的副作用就是不易阅读
 * @ConfigurationProperties 注解可以把properties文件转化为bean，然后使用 @Component 注解把该bean注入到IOC容器中
 */
@ConfigurationProperties(prefix = "remote", ignoreUnknownFields = false)
//@PropertySource(value = "classpath:remote.properties", encoding = "utf-8")
@PropertySource(value = {"classpath:remote.properties","classpath:application.yml"},encoding = "utf-8")
@Data
@Component
public class RemoteProperties {

    private String notFoundUser;
    private String businessErrorMsg;
    private String sysErrorMsg;
    private String serverProviderPort;

}