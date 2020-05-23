package com.xxx.project.serviceImpl;

import com.xxx.project.common.MsgException;
import com.xxx.project.entity.User;
import com.xxx.project.repository.UserRepository;
import com.xxx.project.service.UserService;
import com.xxx.project.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    /**
     *
     * @param mark
     * @return 用户信息筛选列表
     */

    @Override
    public List<User> findByMark(Integer mark) {
        return userRepository.findAllByMark(mark);
    }


    /**
     *
     * @param user 用户信息
     * @return 用户信息
     */
    @Override
    public User add(User user) throws MsgException {
        User old = userRepository.findByName(user.getName());
        if (old != null) {
            throw new MsgException("该账号已经存在!");
        }
        return userRepository.save(user);
    }

    /**
     *
     * @param user 用户信息
     * @return 用户信息
     */
    @Override
    public User reset(User user) {
        user.setPassWord(Md5Util.md5("123456"));
        user.setModifyDate(Calendar.getInstance().getTime());
        return userRepository.save(user);
    }

    /**
     *
     * @param list 用户id列表
     * @return 删除的条数
     */
    @Override
    public Integer delete(List<String> list) {
        return userRepository.deleteBatchIds(list);
    }

    /**
     *
     * @param pageable 分页参数
     * @param user 用户信息，用于筛选
     * @return
     */
    @Override
    public Page<User> pageUser(Pageable pageable, User user) {
        Specification<User> specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (user != null) {
                if(user.getName() !=null && !user.getName().equals("")){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+user.getName()+ "%"));
                }

            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };
        return userRepository.findAll(specification,pageable);
    }
}
