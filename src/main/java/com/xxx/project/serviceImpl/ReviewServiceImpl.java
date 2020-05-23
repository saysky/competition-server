package com.xxx.project.serviceImpl;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Review;
import com.xxx.project.repository.ReviewRepository;
import com.xxx.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    // 新增一条审核信息
    @Override
    public Review add(Review review) throws MsgException {
        // 如果已经报名就抛出异常
        Review temp = reviewRepository.findByCompetitionIdAndUserId(review.getCompetitionId(),review.getUserId());
        if (temp != null) throw new MsgException("您已经报名参赛,请勿重复报名");
        return reviewRepository.save(review);
    }
    // 删除一条审核信息
    @Override
    public void delete(String id) {
        reviewRepository.deleteById(id);
    }
    // 更新一条审核信息
    @Override
    public Review update(Review review) {
        review.setModifyDate(Calendar.getInstance().getTime());
        return reviewRepository.save(review);
    }
    // 审核信息分页条件查询
    @Override
    public Page<Review> page(PageArgs pageArgs) {
        Pageable pageable = PageRequest.of(pageArgs.getCurrentPage(), pageArgs.getPageSize(), Sort.by(Sort.Direction.DESC, "createDate"));
        Review review = pageArgs.getReview();

            Specification<Review> specification = (Specification<Review>) (root, criteriaQuery, criteriaBuilder) -> {
                    List<Predicate> list = new ArrayList<>();
                if (review != null) {
                    if (review.getUserId() != null && !review.getUserId().equals("")) {
                        list.add(criteriaBuilder.equal(root.get("userId").as(String.class), review.getUserId()));
                    }
                    if (review.getCompetitionName() != null && !review.getCompetitionName().equals("")) {
                        list.add(criteriaBuilder.like(root.get("competitionName").as(String.class),"%" + review.getCompetitionName() + "%"));
                    }
                    if (review.getStudentId() != null && !review.getStudentId().equals("")) {
                        list.add(criteriaBuilder.like(root.get("studentId").as(String.class),"%" + review.getStudentId() + "%"));
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));

            };
        return reviewRepository.findAll(specification,pageable);
    }

    @Override
    public Integer countByCompetitionId(String competitionId) {
        return reviewRepository.countByCompetitionId(competitionId);
    }

    @Override
    public Integer countByCompetitionIdAndStatus(String competitionId, Integer status) {
        return reviewRepository.countByCompetitionIdAndStatus(competitionId, status);
    }

    @Override
    public Integer countByCompetitionIdAndPrize(String competitionId, String prize) {
        return reviewRepository.countByCompetitionIdAndPrize(competitionId, prize);
    }


}
