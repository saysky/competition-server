package com.xxx.project.entity;

import com.xxx.project.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
// 用户信息，用于登陆系统。
public class User extends BaseEntity {
    // 用户名
    private String name;

    // 密码
    private String passWord;

    // 修改密码时用
    @Transient
    private String oldPassWord;

    // mark 权限标识 （0为学生，1为教师，2为管理员）
    private Integer mark;

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
