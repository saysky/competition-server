package com.xxx.project.controller;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.common.MsgException;
import com.xxx.project.entity.User;
import com.xxx.project.repository.UserRepository;
import com.xxx.project.service.UserService;
import com.xxx.project.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/add")
    public User add(@RequestBody User user) throws MsgException {
        user.setPassWord(Md5Util.md5(user.getPassWord()));
        return userService.add(user);
    }

    @GetMapping(value = "/list/{mark}")
    public List<User> users(@PathVariable(value = "mark") Integer mark){
        return  userService.findByMark(mark);
    }

    @DeleteMapping(value = "/delete")
    public Integer delete(@RequestBody List<String> ids) {
        return userService.delete(ids);
    }

    @PutMapping(value = "/reset")
    public User reset(@RequestBody User user) {
        return userService.reset(user);
    }

    @PutMapping(value = "/update")
    public User update(@RequestBody User user) throws MsgException {

        User user1 = userRepository.findById(user.getId()).get();
        if (!user1.getPassWord().equals(Md5Util.md5(user.getOldPassWord()))) {
            throw new MsgException("原密码错误!");
        }
        user1.setPassWord(Md5Util.md5(user.getPassWord()));
        user1.setModifyDate(Calendar.getInstance().getTime());
        return userRepository.save(user1);
    }
    @PostMapping(value = "/page")
    public Page<User> pageUser(@RequestBody PageArgs args){
        Pageable pageable = PageRequest.of(args.getCurrentPage(), args.getPageSize(), Sort.by(Sort.Direction.DESC, "createDate"));
        return userService.pageUser(pageable,args.getUser());
    }
}
