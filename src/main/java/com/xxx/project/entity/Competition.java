package com.xxx.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxx.project.base.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table
public class Competition  extends BaseEntity {
    // 竞赛名称
    private String title;

    // 截至日期
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date deadline;

    //竞赛描述
    private String info;

    // 参赛资格描述
    private String qualifications;

    // 参赛年级
    private String gradeNum;


    // 报名总人数
    @Transient
    private Integer totalCount;
    // 通过审核人数
    @Transient
    private Integer checkCount;
    // 未通过审核人数
    @Transient
    private Integer notCheckCount;

    // 一等奖人数
    @Transient
    private Integer firstCount;
    // 二等奖人数
    @Transient
    private Integer secondCount;
    // 三等奖人数
    @Transient
    private Integer thirdCount;
    // 优秀奖人数
    @Transient
    private Integer fourthCount;
    // 为获奖人数
    @Transient
    private Integer noAwardCount;

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    public Integer getNotCheckCount() {
        return notCheckCount;
    }

    public void setNotCheckCount(Integer notCheckCount) {
        this.notCheckCount = notCheckCount;
    }

    public Integer getFirstCount() {
        return firstCount;
    }

    public void setFirstCount(Integer firstCount) {
        this.firstCount = firstCount;
    }

    public Integer getSecondCount() {
        return secondCount;
    }

    public void setSecondCount(Integer secondCount) {
        this.secondCount = secondCount;
    }

    public Integer getThirdCount() {
        return thirdCount;
    }

    public void setThirdCount(Integer thirdCount) {
        this.thirdCount = thirdCount;
    }

    public Integer getFourthCount() {
        return fourthCount;
    }

    public void setFourthCount(Integer fourthCount) {
        this.fourthCount = fourthCount;
    }

    public Integer getNoAwardCount() {
        return noAwardCount;
    }

    public void setNoAwardCount(Integer noAwardCount) {
        this.noAwardCount = noAwardCount;
    }
}
