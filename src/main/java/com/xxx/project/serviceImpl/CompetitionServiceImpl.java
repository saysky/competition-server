package com.xxx.project.serviceImpl;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.entity.Competition;
import com.xxx.project.repository.CompetitionRepository;
import com.xxx.project.service.CompetitionService;
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
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;

    // 新增一条赛事记录
    @Override
    public Competition add(Competition competition) {
        return competitionRepository.save(competition);
    }

    // 删除一条赛事记录
    @Override
    public void delete(String id) {
        competitionRepository.deleteById(id);
    }
    // 更新一条 赛事记录
    @Override
    public Competition update(Competition competition) {
        competition.setModifyDate(Calendar.getInstance().getTime());
        return competitionRepository.save(competition);
    }
    // 赛事记录分页查询
    @Override
    public Page<Competition> page(PageArgs pageArgs) {
        Pageable pageable = PageRequest.of(pageArgs.getCurrentPage(), pageArgs.getPageSize(), Sort.by(Sort.Direction.DESC, "createDate"));
        Competition competition = pageArgs.getCompetition();

        Specification<Competition> specification = (Specification<Competition>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (competition != null) {
                if (competition.getTitle() != null && !competition.getTitle().equals("")) {
                    list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + competition.getTitle() + "%"));
                }
                if (competition.getGradeNum() != null && !competition.getGradeNum().equals("")) {
                    list.add(criteriaBuilder.equal(root.get("gradeNum").as(String.class),competition.getGradeNum()));
                }
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));

        };
        return competitionRepository.findAll(specification,pageable);
    }
}
