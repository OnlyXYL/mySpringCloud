package top.wikl.service_provider.service.redis;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import top.wikl.service_provider.util.RedisSessionPropertiesUtil;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * session缓存操作服务
 *
 * @param
 * @author XiaYaLing
 * @date 2018/1/16
 * @return
 */
public class SessionRedisCacheService extends AbstractBaseRedisCacheService {

    @Resource(name = "redisSessionTemplateSingle")
    private RedisTemplate redisTemplateSingle;

    @Resource(name = "redisSessionTemplateCluster")
    private RedisTemplate redisTemplateCluster;

    @Override
    public RedisTemplate getRedisTemplate() {
        /**
         * 判断是 redis 单节点还是集群
         */
        if (RedisSessionPropertiesUtil.getProperty("session.openCluster").equals("true")) {
            return this.redisTemplateCluster;
        } else if (RedisSessionPropertiesUtil.getProperty("session.openCluster").equals("false")) {
            return this.redisTemplateSingle;
        }
        return null;
    }

    /**
     * 缓存session
     *
     * @param key
     * @param session
     * @return void
     * @author XiaYaLing
     * @date 2018/1/16
     */
    public void setCacheSession(String key, Session session) {
        BoundValueOperations<String, Object> sessionValueOperations = this.getRedisTemplate().boundValueOps("session_" + key);
        sessionValueOperations.set(session);
        sessionValueOperations.expire(this.getExpireTime(), TimeUnit.SECONDS);
    }

    /**
     * 获取缓存session
     *
     * @param key
     * @return org.apache.shiro.session.Session
     * @author XiaYaLing
     * @date 2018/1/16
     */
    public Session getCacheSession(String key) {

        BoundValueOperations<String, Object> sessionValueOperations = this.getRedisTemplate().boundValueOps("session_" + key);
        return (Session) sessionValueOperations.get();
    }
}
