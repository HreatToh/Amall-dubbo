package org.amall.dubbo.service;

import java.util.Map;

public interface RedisService {

    /**
     * 判断key是否存在
     * @param key
     * @return
     * @throws Exception
     */
    boolean isExistsKey(String key) throws Exception;

    /**
     * 判断key是否存在
     * @param key
     * @return
     * @throws Exception
     */
    boolean isNotExistsKey(String key) throws Exception;

    /**
     * 存储map缓存
     * @param key
     * @param map
     * @throws Exception
     */
    void setForMap(String key, Map<String, Object> map) throws Exception;

    /**
     * 根据主key 和 subkey 获取对象
     * @param key
     * @param subKey
     * @return
     */
    Object getForMap(String key, String subKey) throws Exception;
}
