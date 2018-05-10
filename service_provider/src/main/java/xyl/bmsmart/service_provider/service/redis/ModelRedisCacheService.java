package xyl.bmsmart.service_provider.service.redis;

import org.springframework.data.redis.core.RedisTemplate;
import xyl.bmsmart.service_provider.util.RedisModelPropertiesUtil;

import javax.annotation.Resource;

/**
 *    model 缓存操作服务
 * @author XiaYaLing
 * @date 2018/1/16
 * @param
 * @return
 */
public class ModelRedisCacheService extends AbstractBaseRedisCacheService{

    @Resource(name = "redisModelTemplateSingle")
    private RedisTemplate redisTemplateSingle;

    @Resource(name = "redisModelTemplateCluster")
    private RedisTemplate redisTemplateCluster;

    @Override
    public RedisTemplate getRedisTemplate() {
        /**
         * 判断是 redis 单节点还是集群
         */
        if (RedisModelPropertiesUtil.getProperty("model.openCluster").equals("true")) {
            return this.redisTemplateCluster;
        } else if(RedisModelPropertiesUtil.getProperty("model.openCluster").equals("false")){
            return this.redisTemplateSingle;
        }
        return null;
    }
}
