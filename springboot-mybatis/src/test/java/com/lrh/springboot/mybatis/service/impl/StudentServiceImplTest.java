package com.lrh.springboot.mybatis.service.impl;

import com.lrh.mybatis.pagehelper.PageInfo;
import com.lrh.mybatis.pagehelper.PageVO;
import com.lrh.springboot.mybatis.BaseJunitTest;
import com.lrh.springboot.mybatis.entity.Student;
import com.lrh.springboot.mybatis.service.IStudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImplTest extends BaseJunitTest {
    @Autowired
    IStudentService studentService;
    @Test
    public void test(){
        Student student=new Student();
        student.setSName("name1");
        student.setIAge(18);
        student.setSPwd("pwd1");
        studentService.insertSelective(student);
    }

    @Test
    public void test2(){
        List<Student> studentList=new ArrayList<>();

        for (int i = 0; i <100 ; i++) {
            Student student=new Student();
            student.setSName("name"+i);
            student.setIAge(18+i);
            student.setSPwd("pwd"+i);
            studentList.add(student);

            //dddddddddd
        }
        studentService.batchInsertAllColumn(studentList);
//        void insertSelective(T t);
//
//        void batchInsertAllColumn(List<T> t);
//
//        void batchInsertAllColumn(List<T> t, int batchNum);
//
//        void updateSelectiveById(T t);
//
//        void batchUpdateSelectiveById(List<T> t);
//
//        void batchUpdateSelectiveById(List<T> t, int batchNum);
//
//        void deleteById(Serializable id);
//
//        void deleteByColumn(T t);
//
//        void deleteByColumn(Map<String, Object> paramMap);
//
//        T selectById(Serializable id);
//
//        T selectOneByColumn(T t);
//
//        List<T> selectAll();
//
//        List<T> selectListByColumn(T t);
//
//        List<T> selectList(Map<String, Object> paramMap);
//
//        PageInfo<T> selectPage(Map<String, Object> paramMap, PageVO pageVO);
    }


    @Test
    public void test3(){
       Student student=new Student();
        student.setIId(1);
        student.setSName("gaimingzi");
        studentService.updateSelectiveById(student);
    }

}
