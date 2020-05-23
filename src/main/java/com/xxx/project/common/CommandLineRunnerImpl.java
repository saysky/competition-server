package com.xxx.project.common;

import com.xxx.project.entity.User;
import com.xxx.project.repository.UserRepository;
import com.xxx.project.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findByName("admin");
        if (user == null) {
            User user1 = new User();
            user1.setName("admin");
            user1.setMark(2);
            user1.setPassWord(Md5Util.md5("123456"));
            System.out.println("超级用户新建成功");
            userRepository.save(user1);
        }
    }
}
