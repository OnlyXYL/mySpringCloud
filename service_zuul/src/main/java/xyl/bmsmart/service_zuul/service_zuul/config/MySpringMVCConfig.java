package xyl.bmsmart.service_zuul.service_zuul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyl.bmsmart.service_zuul.service_zuul.interceptor.PrintLogInterceptor;

@Configuration
public class MySpringMVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new PrintLogInterceptor()).addPathPatterns("/**");
    }
}
