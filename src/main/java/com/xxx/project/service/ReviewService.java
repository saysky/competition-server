package com.xxx.project.service;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.Future;

public interface ReviewService {
    Review add(Review review) throws MsgException;
    void delete(String id);
    Review update(Review review);
    Page<Review> page(PageArgs pageArgs);



    Integer countByCompetitionId(String competitionId);
    Integer countByCompetitionIdAndStatus(String competitionId, Integer status);
    Integer countByCompetitionIdAndPrize(String competitionId, String prize);
}
