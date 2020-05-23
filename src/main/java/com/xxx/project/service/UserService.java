package com.xxx.project.service;

import com.xxx.project.common.MsgException;
import com.xxx.project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User add(User user) throws MsgException;
    User reset(User user);
    Integer delete(List<String> list);
    Page<User> pageUser(Pageable pageable,User user);
    List<User> findByMark(Integer mark);
}
