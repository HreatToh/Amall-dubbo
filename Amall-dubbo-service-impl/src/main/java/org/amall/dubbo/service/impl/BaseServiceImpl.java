package org.amall.dubbo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.amall.dubbo.common.DubboConfig;
import org.amall.dubbo.common.RedisConstants;
import org.amall.dubbo.common.ResponseCode;
import org.amall.dubbo.entity.PageEntity;
import org.amall.dubbo.mapper.BaseMapper;
import org.amall.dubbo.service.BaseService;
import org.amall.dubbo.service.RedisService;
import org.amall.dubbo.utils.StrUtils;
import org.amall.dubbo.utils.TextTemplateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 设置service的dubbo的超时时间
 **/
@Service(timeout = DubboConfig.SERVICE_TIME_OUT, version = DubboConfig.SERVICE_VERSION, interfaceClass = BaseService.class)
@Component
public class BaseServiceImpl implements BaseService {


    private final Logger logger = LogManager.getLogger(this.getClass());

    public final static String SQL_ID_TEMPLATE = "Class:{},Method:{}";

    @Autowired
    private BaseMapper baseMapper;

    @Autowired
    private RedisService redisService;

    public List<Map<String, Object>> list(Map<String, Object> params) throws Exception {
        String sql = getSql("list", params);
        return baseMapper.list(sql);
    }

    public PageEntity<Map<String, Object>> page(Map<String, Object> params) throws Exception {
        Integer page = Convert.toInt(params.get("page"), 0);
        Integer limit = Convert.toInt(params.get("limit"), 20);
        PageHelper.startPage(page, limit);
        List<Map<String, Object>> list = list(params);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return new PageEntity<Map<String, Object>>(pageInfo.getList(), pageInfo.getTotal(), ResponseCode.SUCCESS_MSG, ResponseCode.SUCCESS_CODE, true);
    }

    public int batchInsert(Map<String, Object> params) throws Exception {
        int count = 0;
        List<Map<String, Object>> list = Convert.convert(List.class, params.get("list"));
        for (Map<String, Object> object : list) {
            count += insertOne(object);
        }
        return count;
    }

    public int insertOne(Map<String, Object> params) throws Exception {
        String sql = getSql("insertOne", params);
        return baseMapper.insertOne(sql);
    }

    public int batchUpdate(Map<String, Object> params) throws Exception {
        int count = 0;
        List<Map<String, Object>> list = Convert.convert(List.class, params.get("list"));
        for (Map<String, Object> object : list) {
            count += updateOne(object);
        }
        return count;
    }

    public int updateOne(Map<String, Object> params) throws Exception {
        String sql = getSql("updateOne", params);
        return baseMapper.updateOne(sql);
    }

    public int batchDelete(Map<String, Object> params) throws Exception {
        int count = 0;
        List<Map<String, Object>> list = Convert.convert(List.class, params.get("list"));
        for (Map<String, Object> object : list) {
            count += deleteOne(object);
        }
        return count;
    }

    public int deleteOne(Map<String, Object> params) throws Exception {
        String sql = getSql("deleteOne", params);
        return baseMapper.deleteOne(sql);
    }

    /**
     * 获取SQL的方法
     *
     * @param methodName
     * @param params
     * @return
     * @throws Exception
     */
    public String getSql(String methodName, Map<String, Object> params) throws Exception {
        String sql = "";
        String sqlSelect = "";
        String sqlId = StrUtils.md5(SQL_ID_TEMPLATE, this.getClass(), methodName);
        Map<String, Object> ayncSql = getRedisAyncSqlById(sqlId);
        String sqlName = StrUtils.emptyToDefault(ayncSql.get("sqlName"), "");
        String sqlContent = StrUtils.emptyToDefault(ayncSql.get("sqlContent"), "");
        String sqlParams = StrUtils.emptyToDefault(ayncSql.get("sqlParams"), "");
        String sqlCondition = StrUtils.emptyToDefault(ayncSql.get("sqlCondition"), " 1=1 ");
        logger.info("SQL_ID:{}", sqlId);
        logger.info("SQL_NAME:{}", sqlName);
        logger.info("SQL_CONTENT:{}", sqlContent);
        logger.info("SQL_PARAMS:{}", sqlParams);
        logger.info("SQL_CONDITION:{}", sqlCondition);
        if (StrUtil.isNotBlank(sqlContent)) {
            sqlCondition = TextTemplateUtils.formatStr(sqlCondition, params);
            sqlSelect = getConditionSqlByParams(sqlParams, params);
            sql = TextTemplateUtils.formatStr(sqlContent, new Dict().set("condition", StrUtil.concat(true, sqlCondition, sqlSelect)));
        } else {
            sql = "SELECT '没有配置SQL' FROM DUAL";
        }
        return sql;
    }


    /**
     * 获取组合查询条件
     *
     * @param sqlParams
     * @param params
     * @return
     * @throws Exception
     */
    private String getConditionSqlByParams(String sqlParams, Map<String, Object> params) throws Exception {
        String sql = "";
        if (StrUtil.isNotBlank(sqlParams)) {
            String[] fieldParams = StrUtil.split(sqlParams, ",");
            /*** key:condition***/
            for (String fieldLabel : fieldParams) {
                String[] split = StrUtil.split(fieldLabel, ":");
                if (params.containsKey(split[0])) {
                    sql += StrUtil.concat(true, " AND ", TextTemplateUtils.formatStr(split[1], params));
                }
            }
        }
        return sql;
    }

    /**
     * 从缓存取异步sql
     *
     * @param sqlId
     * @return
     * @throws Exception
     */
    public Map<String, Object> getRedisAyncSqlById(String sqlId) throws Exception {
        Object o1 = null;
        if (redisService.isNotExistsKey(RedisConstants.REDIS_AYNCSQL_KEY)) {
            //双重检测机制 （如果集群的项目,可以了解一下分布式锁）
            synchronized (this) {
                Map<String, Object> ayncSqlMap = CollUtil.newHashMap();
                List<Map<String, Object>> ayncSqlAll = baseMapper.getAyncSqlAll();
                for (Map<String, Object> map : ayncSqlAll) {
                    String sql_id = StrUtils.emptyToDefault(map.get("sqlId"), "");
                    ayncSqlMap.put(sql_id, map);
                }
                redisService.setForMap(RedisConstants.REDIS_AYNCSQL_KEY, ayncSqlMap);
            }
        }
        o1 = redisService.getForMap(RedisConstants.REDIS_AYNCSQL_KEY, sqlId);
        return o1 != null ? (Map<String, Object>) o1 : CollUtil.newHashMap();
    }
}
