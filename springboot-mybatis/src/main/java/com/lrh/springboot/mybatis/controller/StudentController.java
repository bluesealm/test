package com.lrh.springboot.mybatis.controller;

import com.lrh.springboot.mybatis.entity.Student;
import com.lrh.springboot.mybatis.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    IStudentService studentService;

    @GetMapping("/stuedent")
    public List<Student> selectAll() {
        return studentService.selectAll();
    }
}
