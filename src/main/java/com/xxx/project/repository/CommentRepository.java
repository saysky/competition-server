package com.xxx.project.repository;

import com.xxx.project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,String>, JpaSpecificationExecutor<Comment> {

    List<Comment> findByParentId(String parentId);
}
