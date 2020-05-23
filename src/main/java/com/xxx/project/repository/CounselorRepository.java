package com.xxx.project.repository;

import com.xxx.project.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CounselorRepository extends JpaRepository<Counselor,String>, JpaSpecificationExecutor<Counselor> {
    Counselor findByCounselorId(String counselorId);

    // 通过教师号删除（批量删除教师信息）
    @Modifying
    @Transactional
    @Query("delete from Counselor c where c.counselorId in (?1)")
    Integer deleteByCounselorId(List<String> counselorIds);
}
