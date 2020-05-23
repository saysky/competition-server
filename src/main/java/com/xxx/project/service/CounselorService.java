package com.xxx.project.service;

import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Counselor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CounselorService {
    Counselor add (Counselor counselor) throws MsgException;
    Integer delete(List<String> counselorIds);
    Counselor update(Counselor counselor);
    Page<Counselor> pageCounselor(Pageable pageable, Counselor counselor);
}
