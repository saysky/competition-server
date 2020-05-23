package com.xxx.project.serviceImpl;

import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Student;
import com.xxx.project.entity.User;
import com.xxx.project.repository.StudentRepository;
import com.xxx.project.repository.UserRepository;
import com.xxx.project.service.StudentService;
import com.xxx.project.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * @param student 学生信息实体类
     * @return 学生信息
     */
    @Override
    @Transactional
    public Student add(Student student) throws MsgException {

        Student old = studentRepository.findByStudentId(student.getStudentId());
        if (old != null) throw new MsgException("该学号已经使用");
        // 添加学生信息时，也为改学生添加一个系统账号 用户名为学号 默认密码为 123456
        User user = new User();
        user.setName(student.getStudentId());
        // 密码未加密处理
        user.setPassWord(Md5Util.md5("123456"));
        // 简单的权限标识  0 为学生 1 为辅导员 2 为学校（管理员）
        user.setMark(0);
        userRepository.save(user);
        return studentRepository.save(student);
    }

    /**
     * @param studentIds 学生的学号列表
     * @return 删除记录的条数
     */
    @Override
    @Transactional
    public Integer delete(List<String> studentIds) {
        Integer ret = studentRepository.deleteByStudentIds(studentIds);
        // 同时也删除系统中的账号信息
        userRepository.deleteBatch(studentIds);
        return ret;
    }

    /**
     * @param student 学生实体类
     * @return 修改后的学生信息
     */
    @Override
    public Student update(Student student) {
        // 这里不考虑学号修改情况，学号一般是不会改变的，在前端限制不修改。
        student.setModifyDate(Calendar.getInstance().getTime());
        return studentRepository.save(student);
    }

    /**
     * @param pageable 分页参数
     * @param student  学生信息实体类（用做条件筛选）
     * @return
     */
    @Override
    public Page<Student> pageStudent(Pageable pageable, Student student) {
        Specification<Student> specification = (Specification<Student>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (student != null) {
                if (student.getStudentId() != null && !student.getStudentId().equals("")){
                    list.add(criteriaBuilder.like(root.get("studentId").as(String.class),"%" + student.getStudentId() + "%"));
                }
                if (student.getName() !=null && !student.getName().equals("")){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%" + student.getName() + "%"));
                }
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };
        return studentRepository.findAll(specification, pageable);
    }
}
