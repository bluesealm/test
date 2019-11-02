package com.lrh.springboot.mybatis.mapper;

import com.lrh.springboot.mybatis.entity.Student;
import com.lrh.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author lironghui
 * @version 1.0
 * @date 2019-11-02 16:25:43
 */
public interface StudentMapper extends BaseMapper<Student> {
  void testupdate(@Param("id") int id, @Param("name")String name);
}