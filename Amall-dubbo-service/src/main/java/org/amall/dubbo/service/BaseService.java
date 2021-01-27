package org.amall.dubbo.service;

import org.amall.dubbo.entity.PageEntity;

import java.util.List;
import java.util.Map;

public interface BaseService {
    /**
     * 获取所有信息
     * @param params
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> list(Map<String,Object> params) throws  Exception;

    /**
     * 获取分页信息
     * @param params
     * @return
     * @throws Exception
     */
    public PageEntity page(Map<String,Object> params) throws Exception;


    /**
     * 批量新增
     * @param params
     * @return
     * @throws Exception
     */
    public int batchInsert(Map<String,Object> params) throws Exception;


    /**
     * 新增
     * @param params
     * @return
     * @throws Exception
     */
    public int insertOne(Map<String,Object> params) throws Exception;


    /**
     * 批量修改
     * @param params
     * @return
     * @throws Exception
     */
    public int batchUpdate(Map<String,Object> params) throws Exception;


    /**
     * 修改
     * @param params
     * @return
     * @throws Exception
     */
    public int updateOne(Map<String,Object> params) throws Exception;


    /**
     * 批量删除
     * @param params
     * @return
     * @throws Exception
     */
    public int batchDelete(Map<String,Object> params) throws Exception;


    /**
     * 删除
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteOne(Map<String,Object> params) throws Exception;
}
