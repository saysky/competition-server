package com.xxx.project.entity;

import com.xxx.project.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

// 站内信息
@Entity
@Table
public class Message extends BaseEntity {
    //用户id
    private String userId;

    //接收者姓名
    private String name;

    //消息体
    private String content;

    // 0 未读消息  1 已读消息  2 回收站消息
    private Integer status;

    // 创建者id
    private String createUserId;

    @Transient
    private List<String> userIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
