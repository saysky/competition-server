package com.xxx.project.repository;

import com.xxx.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String>, JpaSpecificationExecutor<Student> {
    Student findByStudentId(String studentId);

    // 通过学号删除（批量删除学生信息）
    @Modifying
    @Transactional
    @Query("delete from Student s where s.studentId in (?1)")
    Integer deleteByStudentIds(List<String> studentIds);
}
