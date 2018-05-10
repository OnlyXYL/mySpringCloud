package xyl.bmsmart.service_provider.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 获取redis session 配置信息
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/3
 * @return
 */
public class RedisSessionPropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);
    private static Properties props;
    private static Map<String, String> map;
    public BufferedReader br = null;


    @Resource
    private static PropertiesFactoryBean configProperties;

    public static PropertiesFactoryBean getConfigProperties() {
        return configProperties;
    }

    public static void loadProps() {
        logger.info("开始加载properties文件内容.......");
        map = new HashMap<>();
        props = new Properties();
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(MessageUtil.class.getClassLoader().getResourceAsStream("redis_session.properties"),
                    "UTF-8");
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String property = props.getProperty(key);
                map.put(key, property);
            }

        } catch (FileNotFoundException e) {
            logger.error("system.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("system.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }

    public static String getProperty(String key) {
        if (null == props && null == map) {
            loadProps();
        }
        return map.get(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props && null == map) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}
