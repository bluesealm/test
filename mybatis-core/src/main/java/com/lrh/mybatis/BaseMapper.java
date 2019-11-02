package com.lrh.mybatis;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/8/10 21:56
 */

public interface BaseMapper<T> {
    
    void insertSelective(T t);

    void batchInsertAllColumn(List<T> t);

    void updateSelectiveById(T t);

    void batchUpdateSelectiveById(List<T> t);

    void deleteById(Serializable id);

    void deleteByColumn(Map<String, Object> paramMap);

    T selectById(Serializable id);

    List<T> selectList(Map<String, Object> paramMap);

    List<T> selectList(Map<String, Object> paramMap, RowBounds rowBounds);
}

