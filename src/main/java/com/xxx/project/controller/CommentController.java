package com.xxx.project.controller;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.entity.Comment;
import com.xxx.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping(value = "/add")
    public Comment add(@RequestBody Comment comment){
        if(StringUtils.isEmpty(comment.getParentId())) {
            comment.setParentId("-1");
        }
        return commentService.add(comment);
    }
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        commentService.delete(id);
    }
    @PutMapping(value = "/update")
    public Comment update(@RequestBody Comment comment) {
        return commentService.update(comment);
    }
    @PostMapping(value = "/page/{id}")
    public Page<Comment> page(@RequestBody PageArgs args,@PathVariable(value = "id")String id) {
        Page<Comment> commentPage =  commentService.page(args,id);
        for(Comment comment : commentPage.getContent()) {
            comment.setChildCommentList(commentService.findByParentId(comment.getId()));
        }
        return commentPage;
    }
}
