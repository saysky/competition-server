package com.xxx.project.repository;

import com.xxx.project.entity.PlanFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PlanFileRepository extends JpaRepository<PlanFile,String>, JpaSpecificationExecutor<PlanFile> {
    List<PlanFile> findAllByExtraId(String id);
}
