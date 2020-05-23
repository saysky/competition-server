package com.xxx.project.service;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    Comment add(Comment comment);
    void delete(String id);
    Comment update(Comment comment);
    Page<Comment> page(PageArgs pageArgs,String id);

    List<Comment> findByParentId(String parentId);
}
