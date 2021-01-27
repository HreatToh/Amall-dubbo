package org.amall.dubbo.service.impl;

import org.amall.dubbo.annotation.WebLogger;
import org.amall.dubbo.entity.PageEntity;
import org.amall.dubbo.service.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class BaseServiceImpl implements BaseService {

    @WebLogger(value = "list(Map<String, Object> params) ",type = BaseServiceImpl.class)
    @Override
    public List<Map<String, Object>> list(Map<String, Object> params) throws Exception {
        return null;
    }
    @WebLogger(value = "page(Map<String, Object> params) ",type = BaseServiceImpl.class)
    @Override
    public PageEntity page(Map<String, Object> params) throws Exception {
        return null;
    }
    @WebLogger(value = "batchInsert(Map<String, Object> params) ",type = BaseServiceImpl.class)
    @Override
    public int batchInsert(Map<String, Object> params) throws Exception {
        return 0;
    }
    @WebLogger(value = "insertOne(Map<String, Object> params) ",type = BaseServiceImpl.class)
    @Override
    public int insertOne(Map<String, Object> params) throws Exception {
        return 0;
    }
    @WebLogger(value = "batchUpdate(Map<String, Object> params) ",type = BaseServiceImpl.class)
    @Override
    public int batchUpdate(Map<String, Object> params) throws Exception {
        return 0;
    }
    @WebLogger(value = "updateOne(Map<String, Object> params) ",type = BaseServiceImpl.class)
    @Override
    public int updateOne(Map<String, Object> params) throws Exception {
        return 0;
    }
    @WebLogger(value = "batchDelete(Map<String, Object> params) ",type = BaseServiceImpl.class)
    @Override
    public int batchDelete(Map<String, Object> params) throws Exception {
        return 0;
    }
    @WebLogger(value = "deleteOne(Map<String, Object> params) ",type = BaseServiceImpl.class)
    @Override
    public int deleteOne(Map<String, Object> params) throws Exception {
        return 0;
    }
}
