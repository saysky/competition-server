package com.xxx.project.entity;

import com.xxx.project.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
// 教师信息实体类
public class Counselor extends BaseEntity {

    //教师Id
    private String counselorId;

    //姓名
    private String name;

    //性别
    private boolean gender;

    //学院
    private String College;

    // 身份证号码
    private String code;

    //电话
    private String phoneNumber;

    public boolean isGender() {
        return gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(String counselorId) {
        this.counselorId = counselorId;
    }
}
