package xyl.bmsmart.service_provider.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;

    private Logger Log = LoggerFactory.getLogger(MybatisScannerConfig.class);

    /**
     * 配置sqlSessionFactoryBean
     *
     * @param
     * @return org.mybatis.spring.SqlSessionFactoryBean
     * @author XiaYaLing
     * @date 2018/4/19
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        Log.debug(":::start 加载:::" + this.getClass().getName());
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:mybatis/mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        return sqlSessionFactoryBean.getObject();
    }
}
