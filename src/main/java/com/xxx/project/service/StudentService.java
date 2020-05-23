package com.xxx.project.service;

import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    Student add (Student student) throws MsgException;
    Integer delete(List<String> studentIds);
    Student update(Student student);
    Page<Student> pageStudent(Pageable pageable,Student student);
}
