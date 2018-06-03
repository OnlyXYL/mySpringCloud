package top.wikl.service_provider.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;


/**
 * Description:
 * <p>
 * 与applicationContext.xml同等效果
 *
 * @author dclar
 */
//@Configuration
@PropertySource("classpath:dbconfig.properties")
@ComponentScan("xyl.bmsmart.service_provider")
public class AppConfig {

    /**
     * 用户名
     */
    @Value("${db_username}")
    private String username;

    /**
     * 地址
     */
    @Value("${db_url}")
    private String url;

    /**
     * 密码
     */
    @Value("${db_password}")
    private String password;

    /**
     * 驱动名
     */
    @Value("${db_driverClassName}")
    private String driverClassName;

    /**
     * 过滤器
     */
    @Value("${filters}")
    private String filters;

    /**
     * 最大并发连接数
     */
    @Value("${maxActive}")
    private int maxActive;

    /**
     * 初始化连接数量
     */
    @Value("${initialSize}")
    private int initialSize;

    /**
     * 配置获取连接等待超时的时间
     */
    @Value("${maxWait}")
    private long maxWait;

    /**
     * 最小空闲连接数
     */
    @Value("${minIdle}")
    private int minIdle;

    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    @Value("${timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    @Value("${minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${validationQuery}")
    private String validationQuery;

    @Value("${testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${testOnReturn}")
    private boolean testOnReturn;

    @Value("${maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;

    /**
     * 打开removeAbandoned功能
     */
    @Value("${removeAbandoned}")
    private boolean removeAbandoned;

    /**
     * 1800秒，也就是30分钟
     */
    @Value("${removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    /**
     * 关闭abanded连接时输出错误日志
     */
    @Value("${logAbandoned}")
    private boolean logAbandoned;

    /**
     * 配置数据源
     *
     * @param
     * @return com.alibaba.druid.pool.DruidDataSource
     * @author XiaYaLing
     * @date 2018/4/19
     */
    @Bean(destroyMethod = "close")
    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(username);
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setFilters(filters);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setLogAbandoned(logAbandoned);
        return druidDataSource;
    }
}
