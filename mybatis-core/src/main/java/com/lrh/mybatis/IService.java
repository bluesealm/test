package com.lrh.mybatis;

import com.lrh.mybatis.pagehelper.PageInfo;
import com.lrh.mybatis.pagehelper.PageVO;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/8/10 22:05
 */
public interface IService<T> {
    void insertSelective(T t);

    void batchInsertAllColumn(List<T> t);

    void batchInsertAllColumn(List<T> t, int batchNum);

    void updateSelectiveById(T t);

    void batchUpdateSelectiveById(List<T> t);

    void batchUpdateSelectiveById(List<T> t, int batchNum);

    void deleteById(Serializable id);

    void deleteByColumn(T t);

    void deleteByColumn(Map<String, Object> paramMap);

    T selectById(Serializable id);

    T selectOneByColumn(T t);

    List<T> selectAll();

    List<T> selectListByColumn(T t);

    List<T> selectList(Map<String, Object> paramMap);

    PageInfo<T> selectPage(Map<String, Object> paramMap, PageVO pageVO);
}
