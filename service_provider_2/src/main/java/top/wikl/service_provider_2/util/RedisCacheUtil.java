package top.wikl.service_provider_2.util;

import org.springframework.data.redis.connection.ClusterInfo;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.*;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis缓存管理类
 *
 * @param
 * @author XiaYaLing
 * @date 2018/1/5
 * @return
 */
public class RedisCacheUtil {

    /**
     * reid模板
     */
    private RedisTemplate redisTemplate;

    /**
     * 过期时间
     */
    private long expireTime;

    /**
     * 是否开启缓存   开启：true   不开启： false
     */
    private boolean openCache;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isOpenCache() {
        return openCache;
    }

    public void setOpenCache(boolean openCache) {
        this.openCache = openCache;
    }

    /**
     * 获取集群信息
     *
     * @param
     * @return org.springframework.data.redis.connection.ClusterInfo
     * @author XiaYaLing
     * @date 2018/1/9
     */
    public ClusterInfo getRedisClusterInfo() {
        RedisClusterConnection clusterConnection = redisTemplate.getConnectionFactory().getClusterConnection();
       /* List<RedisClientInfo> clientList = clusterConnection.getClientList();
        System.out.println("ClientList:"+clientList);*/
        ClusterInfo clusterInfo = clusterConnection.clusterGetClusterInfo();
        System.out.println("Redis Cluster info : " + clusterInfo);
        return clusterInfo;
    }

    /**
     * 获取集群节点
     *
     * @param
     * @return void
     * @author XiaYaLing
     * @date 2018/1/9
     */
    public void getRedisNodeInfo() {
        RedisClusterConnection clusterConnection = redisTemplate.getConnectionFactory().getClusterConnection();
        Iterable<RedisClusterNode> redisClusterNodes = clusterConnection.clusterGetNodes();
        Iterator<RedisClusterNode> iterator = redisClusterNodes.iterator();
        while (iterator.hasNext()) {
            System.out.println("Redis Cluster node : " + iterator.next());
        }
    }

    /**
     * 获取指定key的过期时间
     *
     * @param key 缓存键
     * @return long
     * 过期时间(秒)
     * @author XiaYaLing
     * @date 2018/1/9
     */
    public long getTtl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取指定key的过期时间
     *
     * @param key 缓存键
     * @return long
     * 过期时间(毫秒)
     * @author XiaYaLing
     * @date 2018/1/9
     */
    public long getPTtl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

    /**
     * 删除指定键的所有数据
     *
     * @param key 缓存键
     * @return void
     * @author XiaYaLing
     * @date 2018/1/9
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 判断指定key是否存在
     *
     * @param key 缓存键
     * @return boolean
     * 结果 存在为true，不存在为 false
     * @author XiaYaLing
     * @date 2018/1/9
     */
    public boolean IsHaveKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键
     * @param value 缓存的值
     * @return org.springframework.data.redis.core.ValueOperations<java.lang.String,T>
     * 缓存数据的对象
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {

        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        if (expireTime > 0) {
            redisTemplate.expire(key, this.expireTime, TimeUnit.SECONDS);
        }
        return operation;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键
     * @return T
     * 缓存键对应的数据
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键
     * @param dataList 待缓存的List数据
     * @return org.springframework.data.redis.core.ListOperations<java.lang.String,T>
     * 缓存数据的对象
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.rightPush(key, dataList.get(i));
            }
        }
        if (expireTime > 0) {
            redisTemplate.expire(key, this.expireTime, TimeUnit.SECONDS);
        }

        return listOperation;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键
     * @return java.util.List<T>
     * 缓存键对应的数据
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);
        for (int i = 0; i < size; i++) {
            dataList.add((T) listOperation.leftPop(key));
        }
        return dataList;
    }

    /**
     * 缓存无序Set
     *
     * @param key     缓存键
     * @param dataSet 缓存的数据
     * @return org.springframework.data.redis.core.BoundSetOperations<java.lang.String,T>
     * 缓存数据的对象
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
        this.delete(key);
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        /*T[] t = (T[]) dataSet.toArray();
    setOperation.add(t);*/
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        if (expireTime > 0) {
            redisTemplate.expire(key, this.expireTime, TimeUnit.SECONDS);
        }

        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key 缓存键
     * @return java.util.Set<org.apache.poi.ss.formula.functions.T>
     * 缓存键对应的数据
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public Set<Object> getCacheSet(String key) {
        Set<Object> dataSet = new HashSet<Object>();
        BoundSetOperations<String, Object> operation = redisTemplate.boundSetOps(key);

        Long size = operation.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(operation.pop());
        }
        return dataSet;
    }

    /**
     * 缓存有序Set
     * Redis 有序集合和集合一样也是string类型元素的集合,且不允许重复的成员。
     * 不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
     * 有序集合的成员是唯一的,但分数(score)却可以重复。
     *
     * @param key     缓存键
     * @param dataSet 缓存的数据
     * @return org.springframework.data.redis.core.BoundSetOperations<java.lang.String,T>
     * 缓存数据的对象
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> BoundZSetOperations<String, T> setCacheZSet(String key, Set<T> dataSet) {
        this.delete(key);
        BoundZSetOperations<String, T> setOperation = redisTemplate.boundZSetOps(key);
        T[] t = (T[]) dataSet.toArray();
        for (int i = 0; i < t.length; i++) {
            System.out.println("zset:" + t[i]);
            setOperation.add(t[i], i);
        }
        if (expireTime > 0) {
            redisTemplate.expire(key, this.expireTime, TimeUnit.SECONDS);
        }

        return setOperation;
    }

    /**
     * 获得缓存的有序set(待实现)
     *
     * @param key 缓存键
     * @return java.util.Set<org.apache.poi.ss.formula.functions.T>
     * 缓存键对应的数据
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public Set<Object> getCacheZSet(String key) {
        BoundZSetOperations<String,Object> operation = redisTemplate.boundZSetOps(key);
        Long size = operation.size();
        Set<Object> dataSet = operation.range(0, size - 1);

        return dataSet;
    }


    /**
     * 缓存Map
     *
     * @param key     缓存键
     * @param dataMap 缓存的数据
     * @return org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.String,T>
     * 缓存数据的对象
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {

        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {

            for (Map.Entry<String, T> entry : dataMap.entrySet()) {

                /*System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); */
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }

        }
        if (expireTime > 0) {
            redisTemplate.expire(key, this.expireTime, TimeUnit.SECONDS);
        }

        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key 缓存键
     * @return java.util.Map<java.lang.String,T>
     * 缓存键对应的数据
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> Map<String, T> getCacheMap(String key/*,HashOperations<String,String,T> hashOperation*/) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        /*Map<String, T> map = hashOperation.entries(key);*/
        return map;
    }

    /**
     * 缓存Map
     *
     * @param key     缓存键
     * @param dataMap 缓存的数据
     * @return org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.Integer,T>
     * 缓存数据的对象
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> HashOperations<String, Integer, T> setCacheIntegerMap(String key, Map<Integer, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
                /*System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); */
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        if (expireTime > 0) {
            redisTemplate.expire(key, this.expireTime, TimeUnit.SECONDS);
        }

        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key 缓存键
     * @return java.util.Map<java.lang.Integer,T>
     * 缓存键对应的数据
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public <T> Map<Integer, T> getCacheIntegerMap(String key/*,HashOperations<String,String,T> hashOperation*/) {
        Map<Integer, T> map = redisTemplate.opsForHash().entries(key);
        /*Map<String, T> map = hashOperation.entries(key);*/
        return map;
    }

    /**
     * byte[]转Object
     *
     * @param byteValue 字节数组
     * @return java.lang.Object
     * 转成的对象
     * @author XiaYaLing
     * @date 2018/1/8
     */
    public Object byteToObject(byte[] byteValue) {
        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new ByteArrayInputStream(byteValue));
            Object obj = inputStream.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}