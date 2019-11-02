package com.lrh.springboot.mybatis.service.impl;

import com.lrh.springboot.mybatis.entity.StudentCopy;
import com.lrh.mybatis.ServiceImpl;
import com.lrh.springboot.mybatis.mapper.StudentCopyMapper;
import com.lrh.springboot.mybatis.service.IStudentCopyService;
import org.springframework.stereotype.Service;
/**
 * @author lironghui
 * @version 1.0
 * @date 2019-11-02 16:38:59
 */
@Service
public class StudentCopyServiceImpl extends ServiceImpl<StudentCopyMapper,StudentCopy>  implements IStudentCopyService<StudentCopy>{

}