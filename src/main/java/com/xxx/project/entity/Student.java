package com.xxx.project.entity;

import com.xxx.project.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
// 学生信息实体类
public class Student extends BaseEntity {
    // 学号
    private String studentId;

    // 姓名
    private String name;

    //性别 (true 为男性)
    private boolean gender;

    //学院
    private String college;

    //专业
    private String profession;

    // 年级
    private String gradeNum;

    //班级
    private String classNum;

    //身份证号
    private String code;

    //电话
    private String phoneNumber;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGradeNum() { return gradeNum; }

    public void setGradeNum(String gradeNum) { this.gradeNum = gradeNum; }

    public String getClassNum() { return classNum; }

    public void setClassNum(String classNum) { this.classNum = classNum; }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }


}
