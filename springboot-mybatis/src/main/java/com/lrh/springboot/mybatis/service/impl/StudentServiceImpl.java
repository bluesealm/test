package com.lrh.springboot.mybatis.service.impl;

import com.lrh.springboot.mybatis.entity.Student;
import com.lrh.mybatis.ServiceImpl;
import com.lrh.springboot.mybatis.mapper.StudentMapper;
import com.lrh.springboot.mybatis.service.IStudentService;
import org.springframework.stereotype.Service;
/**
 * @author lironghui
 * @version 1.0
 * @date 2019-11-02 16:25:43
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student>  implements IStudentService<Student>{

}