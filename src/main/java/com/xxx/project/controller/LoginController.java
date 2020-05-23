package com.xxx.project.controller;

import com.xxx.project.bean.Result;
import com.xxx.project.bean.UserInfo;
import com.xxx.project.common.MsgException;
import com.xxx.project.entity.User;
import com.xxx.project.repository.CounselorRepository;
import com.xxx.project.repository.StudentRepository;
import com.xxx.project.repository.UserRepository;
import com.xxx.project.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
//@CrossOrigin(allowCredentials = "true")
@RequestMapping
// 登录控制器
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CounselorRepository counselorRepository;
    @PostMapping(value = "/login")
    public Object login(@RequestBody User user, HttpServletRequest request) throws MsgException {
        User temp = userRepository.findByName(user.getName());
        UserInfo userInfo = new UserInfo();

        // 简单的判断密码，数据库中的密码未加密。
        if (temp == null || !temp.getPassWord().equals(Md5Util.md5(user.getPassWord()))) {
            return new Result(888,"用户名或密码错误");
        } else {
            request.getSession(true).setAttribute("userName",temp.getName());
            // 如果是学生登录，则返回学生信息，如果是教师登录，则返回教师信息，如果为校方登录（最高管理员），则返回账号信息;
            if (temp.getMark() == 0) {
                userInfo.setOther(studentRepository.findByStudentId(temp.getName()));
            } else if (temp.getMark() == 1) {
                userInfo.setOther(counselorRepository.findByCounselorId(temp.getName()));
            }
        }
        userInfo.setUserId(temp.getId());
        userInfo.setUserName(temp.getName());
        userInfo.setMark(temp.getMark());
        return userInfo;
    }
    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("userName");
    }
}
