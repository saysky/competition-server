package com.xxx.project.entity;

import com.xxx.project.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

// 审核信息实体类
@Table
@Entity
public class Review extends BaseEntity {
    // 竞赛名称
    private String competitionName;
    // 竞赛 id
    private String competitionId;
    //报名者姓名
    private String userName;
    // 报名者专业
    private String profession;
    // 报名者id
    private String userId;
    // 报名者学号
    private String studentId;

    // 申请状态  0 审核中  1 已通过  2 未通过
    private Integer status;

    // 额外信息
    private String extra;

    // 报名者获奖情况
    private String prize;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
