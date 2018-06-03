package top.wikl.service_provider.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisScannerConfig {

    private Logger Log = LoggerFactory.getLogger(MybatisScannerConfig.class);

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        Log.debug(":::start 加载:::" + this.getClass().getName());
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("xyl.bmsmart.service_provider.mapper");
        scannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return scannerConfigurer;
    }
}
