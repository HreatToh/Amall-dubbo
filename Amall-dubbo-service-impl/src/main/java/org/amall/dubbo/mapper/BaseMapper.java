package org.amall.dubbo.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseMapper {

    @Select("SELECT * FROM AMALL_AYNC_SQL WHERE SQL_ID = #{sqlId}")
    public Map<String,Object> getAyncSqlById(@Param("sqlId") String sqlId) throws Exception;

    @Select("${sql}")
    List<Map<String, Object>> list(@Param("sql") String sql) throws Exception;

    @Insert("${sql}")
    int insertOne(@Param("sql") String sql) throws Exception;

    @Update("${sql}")
    int updateOne(String sql) throws Exception;

    @Delete("${sql}")
    int deleteOne(String sql) throws Exception;
}
