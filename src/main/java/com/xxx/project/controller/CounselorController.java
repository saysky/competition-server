package com.xxx.project.controller;
import com.xxx.project.bean.PageArgs;
import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Counselor;
import com.xxx.project.repository.CounselorRepository;
import com.xxx.project.service.CounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/counselor")
public class CounselorController {
    @Autowired
    private CounselorService counselorService;

    @Autowired
    private CounselorRepository counselorRepository;

    @GetMapping(value = "/all")
    public List<Counselor> allCounselor() {
        return counselorRepository.findAll();
    }
    @PostMapping(value = "/add")
    public Counselor add(@RequestBody Counselor counselor) throws MsgException {
        return counselorService.add(counselor);
    }
    @DeleteMapping(value = "/delete")
    public Integer delete(@RequestBody List<String> counselorIds) {
        return counselorService.delete(counselorIds);
    }
    @PutMapping(value = "/update")
    public Counselor update(@RequestBody Counselor counselor) {
        return counselorService.update(counselor);
    }
    @PostMapping(value = "/page")
    public Page<Counselor> page(@RequestBody PageArgs args) {
        Pageable pageable = PageRequest.of(args.getCurrentPage(), args.getPageSize(), Sort.by(Sort.Direction.DESC, "createDate"));
        return counselorService.pageCounselor(pageable,args.getCounselor());
    }
}
