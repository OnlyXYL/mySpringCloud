package top.wikl.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.wikl.web.config.RemoteProperties;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取属性信息
 * <p>
 * 问题背景：MessageUtil 中注入 RemoteProperties（RemoteProperties 需要被static 修饰）
 * 1.第一次 MessageUtil 没有加@Component注解 ，注入RemoteProperties 为空，我觉得是因为MessageUtil没有被spring 管理的原因
 * 2.第二次 MessageUtil加上@Component注解（即交给了spring管理）,@resource注解注入报错，@Autowired注解注入为空，
 * <p>
 * 从Java EE5规范开始，Servlet增加了两个影响Servlet生命周期的注解（Annotation）：@PostConstruct和@PreConstruct。这两个注解被用来修饰一个非静态的void()方法.而且这个方法不能有抛出异常声明。
 * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/26
 * @return
 */
@Component
public class MessageUtil {

    @Autowired
    private RemoteProperties remoteProperties;

    public static RemoteProperties properties;

    private static Map<String, String> map;

    @PostConstruct
    public void getRemoteProperties() {
        properties = remoteProperties;
    }

    public static Map<String, String> loadProperty(/*RemoteProperties properties*/) {

//        remoteProperties = properties;

        map = new HashMap<>();

        //获取属性名数组
        Field[] fields = properties.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getType());
            fieldNames[i] = fields[i].getName();
            String value = getFieldValueByName(fieldNames[i], properties);
            map.put(fieldNames[i], value);
        }
        return map;
    }

    /**
     * 根据属性名称获取对应的属性值（通过get方法）
     *
     * @param fieldName
     * @param properties
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/4/26
     */
    private static String getFieldValueByName(String fieldName, RemoteProperties properties) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = properties.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(properties, new Object[]{});
            return value.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据key 获取配置文件信息
     *
     * @param key
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/4/26
     */
    public static String getProperty(String key) {
        if (null == map) {
            loadProperty(/*remoteProperties*/);
        }
        return map.get(key);
    }

}