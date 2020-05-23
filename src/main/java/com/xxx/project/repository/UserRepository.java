package com.xxx.project.repository;

import com.xxx.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    User findByName(String name);


    // 通过用户名批量删除账号信息
    @Modifying
    @Transactional
    @Query("delete from User u where u.name in (?1)")
    Integer deleteBatch(List<String> nameList);

    // 通过id批量删除账号信息
    @Modifying
    @Transactional
    @Query("delete from User u where u.id in (?1)")
    Integer deleteBatchIds(List<String> List);

    List<User> findAllByMark(Integer mark);

}
