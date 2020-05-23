package com.xxx.project.repository;

import com.xxx.project.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompetitionRepository extends JpaRepository<Competition,String>, JpaSpecificationExecutor<Competition> {
}
