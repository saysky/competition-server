package com.xxx.project.controller;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.entity.Competition;
import com.xxx.project.service.CompetitionService;
import com.xxx.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/competition")
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping(value = "/add")
    public Competition add(@RequestBody Competition competition) {
        return competitionService.add(competition);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        competitionService.delete(id);
    }

    @PutMapping(value = "/update")
    public Competition update(@RequestBody Competition competition) {
        return competitionService.update(competition);
    }

    @PostMapping(value = "/page")
    public Page<Competition> page(@RequestBody PageArgs args) {
        Page<Competition> competitionPage = competitionService.page(args);
        for (Competition competition : competitionPage.getContent()) {
            String id = competition.getId();
            competition.setTotalCount(reviewService.countByCompetitionId(id));
            competition.setCheckCount(reviewService.countByCompetitionIdAndStatus(id, 1));
            competition.setNotCheckCount(reviewService.countByCompetitionIdAndStatus(id, 0));
            competition.setFirstCount(reviewService.countByCompetitionIdAndPrize(id, "一等奖"));
            competition.setSecondCount(reviewService.countByCompetitionIdAndPrize(id, "二等奖"));
            competition.setThirdCount(reviewService.countByCompetitionIdAndPrize(id, "三等奖"));
            competition.setFourthCount(reviewService.countByCompetitionIdAndPrize(id, "优秀奖"));
            competition.setNoAwardCount(reviewService.countByCompetitionIdAndPrize(id, null));
        }
        return competitionPage;
    }
}
