package com.xxx.project.entity;

import com.xxx.project.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table
public class Comment extends BaseEntity {

    // 上级评论ID
    private String parentId;

    // 评论人名称
    private String name;

    // 评论竞赛id
    private String competitionId;

    //评论内容
    private String content;

    @Transient
    private List<Comment> childCommentList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getChildCommentList() {
        return childCommentList;
    }

    public void setChildCommentList(List<Comment> childCommentList) {
        this.childCommentList = childCommentList;
    }
}
