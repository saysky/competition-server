package com.xxx.project.service;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.entity.Competition;
import org.springframework.data.domain.Page;

public interface CompetitionService {
    Competition add(Competition competition);
    void delete(String id);
    Competition update(Competition competition);
    Page<Competition> page(PageArgs pageArgs);
}
