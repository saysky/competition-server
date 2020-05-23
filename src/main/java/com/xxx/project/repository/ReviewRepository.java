package com.xxx.project.repository;

import com.xxx.project.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReviewRepository extends JpaRepository<Review,String>, JpaSpecificationExecutor<Review> {
    // 根据 competittionId 和 userId 查询审批记录，避免重复报名
    Review findByCompetitionIdAndUserId(String competitionId,String userId);

    Integer countByCompetitionId(String competitionId);
    Integer countByCompetitionIdAndStatus(String competitionId, Integer status);
    Integer countByCompetitionIdAndPrize(String competitionId, String prize);
}
