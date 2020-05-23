package com.xxx.project.repository;

import com.xxx.project.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,String>, JpaSpecificationExecutor<Message> {
    List<Message> findAllByUserIdAndStatus(String userId,Integer status);

    // 全部标记为已读
    @Modifying
    @Transactional
    @Query("update Message m set m.status = 1 where m.userId = (?1) and m.status = 0")
    void read(String userId);

    // 全部删除
    @Modifying
    @Transactional
    @Query("update Message m set m.status = 2 where m.userId = (?1) and m.status = 1")
    void alldelete(String userId);

    // 清空回收站
    @Modifying
    @Transactional
    @Query("delete from Message m where m.userId = (?1) and m.status = 2")
    void clearMessage(String userId);
}
