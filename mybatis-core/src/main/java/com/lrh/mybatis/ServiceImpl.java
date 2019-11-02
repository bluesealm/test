package com.lrh.mybatis;

import com.lrh.mybatis.pagehelper.PageInfo;
import com.lrh.mybatis.pagehelper.PageVO;
import com.lrh.mybatis.util.ParameterUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/8/10 22:05
 */
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
    private static final int batchNum = 500;
    private static final Map<String, Object> emptyMap = new HashMap<>();
    @Autowired
    protected M mapper;

    @Override
    public void insertSelective(T t) {
        Assert.notNull(t, "insertSelective entity must not be null");
        this.mapper.insertSelective(t);
    }

    @Override
    public void batchInsertAllColumn(List<T> t) {
        this.batchInsertAllColumn(t, batchNum);
    }

    @Override
    public void batchInsertAllColumn(List<T> t, int batchNum) {
        Assert.notEmpty(t, "batchInsert entityList must not be Empty");
        Assert.state(batchNum > 0, "batchNum  must > 0");
        List<T> tempList = new ArrayList<>();
        for (int i = 0; i < t.size(); i++) {
            int start = i * batchNum;
            int end = (i + 1) * batchNum;
            if (end >= t.size()) {
                this.mapper.batchInsertAllColumn(t.subList(start, t.size()));
                break;
            } else {
                this.mapper.batchInsertAllColumn(t.subList(start, end));
            }
        }
    }

    @Override
    public void updateSelectiveById(T t) {
        Assert.notNull(t, "updateSelectiveById entity must not be null");
        this.mapper.updateSelectiveById(t);
    }

    @Override
    public void batchUpdateSelectiveById(List<T> t) {
        this.batchUpdateSelectiveById(t, batchNum);
    }

    @Override
    public void batchUpdateSelectiveById(List<T> t, int batchNum) {
        Assert.notEmpty(t, "batchUpdateById entityList must not be Empty");
        Assert.state(batchNum > 0, "batchNum  must > 0");
        List<T> tempList = new ArrayList<>();
        for (int i = 0; i < t.size(); i++) {
            int start = i * batchNum;
            int end = (i + 1) * batchNum;
            if (end >= t.size()) {
                this.mapper.batchUpdateSelectiveById(t.subList(start, t.size()));
                break;
            } else {
                this.mapper.batchUpdateSelectiveById(t.subList(start, end));
            }
        }
    }

    @Override
    public void deleteById(Serializable id) {
        Assert.notNull(id, "deleteById id must not be null");
        this.mapper.deleteById(id);
    }

    @Override
    public void deleteByColumn(T t) {
        Assert.notNull(t, "deleteByColumn entity must not be null");
        this.deleteByColumn(ParameterUtil.converObjNotEmptyToMap(t));
    }

    @Override
    public void deleteByColumn(Map<String, Object> paramMap) {
        Assert.notEmpty(paramMap, "deleteByColumn paramMap must not be null");
        this.mapper.deleteByColumn(paramMap);
    }


    @Override
    public T selectById(Serializable id) {
        Assert.notNull(id, "selectById id must not be null");
        return this.mapper.selectById(id);
    }

    @Override
    public List<T> selectAll() {
        return this.selectList(emptyMap);
    }

    @Override
    public List<T> selectList(Map<String, Object> paramMap) {
        Assert.notNull(paramMap, "selectList paramMap must not be null");
        return this.mapper.selectList(paramMap);
    }

    @Override
    public T selectOneByColumn(T t) {
        Assert.notNull(t, "selectOneByColumn Entity must not be null");
        List<T> list = this.selectListByColumn(t);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<T> selectListByColumn(T t) {
        Assert.notNull(t, "selectListByColumn Entity must not be null");
        return this.selectList(ParameterUtil.converObjNotEmptyToMap(t));
    }

    @Override
    public PageInfo<T> selectPage(Map<String, Object> paramMap, PageVO pageVO) {
        List<T> list= this.mapper.selectList(paramMap, pageVO);
        PageInfo pageInfo=new PageInfo(pageVO,list);
        return pageInfo;
    }
}
