package org.amall.dubbo.service.impl;

import org.amall.dubbo.common.RedisConstants;
import org.amall.dubbo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 判断是否存在key
     * @param key
     * @return
     * @throws Exception
     */
    public boolean isExistsKey(String key) throws Exception {
        return redisTemplate.hasKey(key);
    }

    /**
     * 判断是否存在key
     * @param key
     * @return
     * @throws Exception
     */
    public boolean isNotExistsKey(String key) throws Exception {
        return !isExistsKey(key);
    }

    /**
     * 存储map缓存
     * @param key
     * @param map
     * @throws Exception
     */
    public void setForMap(String key, Map<String, Object> map) throws Exception {
        redisTemplate.opsForHash().putAll(key,map);
    }

    /**
     * 获取map值通过主key和子subKey
     * @param key
     * @param subKey
     * @return
     * @throws Exception
     */
    public Object getForMap(String key, String subKey) throws Exception {
        return redisTemplate.opsForHash().get(key,subKey);
    }
}
