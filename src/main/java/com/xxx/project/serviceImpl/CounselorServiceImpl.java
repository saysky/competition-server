package com.xxx.project.serviceImpl;

import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Counselor;
import com.xxx.project.entity.User;
import com.xxx.project.repository.CounselorRepository;
import com.xxx.project.repository.UserRepository;
import com.xxx.project.service.CounselorService;
import com.xxx.project.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CounselorServiceImpl implements CounselorService {
    @Autowired
    private CounselorRepository counselorRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     *
     * @param counselor 教师信息实体类
     * @return 教师信息
     */
    @Override
    @Transactional
    public Counselor add(Counselor counselor) throws MsgException {

        Counselor old  = counselorRepository.findByCounselorId(counselor.getCounselorId());
        if (old != null) throw new MsgException("该教师账号已经使用");
        // 添加教师信息时，也为教师添加一个系统账号 用户名为教师号 默认密码为 123456
        User user = new User();
        user.setName(counselor.getCounselorId());
        user.setPassWord(Md5Util.md5("123456"));
        user.setMark(1);
        userRepository.save(user);
        return counselorRepository.save(counselor);
    }

    /**
     *
     * @param counselorIds 教师号列表
     * @return 删除记录的条数
     */
    @Override
    @Transactional
    public Integer delete(List<String> counselorIds) {
        Integer ret = counselorRepository.deleteByCounselorId(counselorIds);
        // 同时也删除系统中的账号信息
        userRepository.deleteBatch(counselorIds);
        return ret;
    }

    /**
     *
     * @param counselor 辅导员实体类
     * @return 修改后的辅导员信息
     */
    @Override
    public Counselor update(Counselor counselor) {
        StringBuffer stringBuffer = new StringBuffer();
        counselor.setModifyDate(Calendar.getInstance().getTime());
        return counselorRepository.save(counselor);
    }

    /**
     *
     * @param pageable 分页参数
     * @param counselor 辅导员信息实体类（用做条件筛选）
     * @return
     */
    @Override
    public Page<Counselor> pageCounselor(Pageable pageable, Counselor counselor) {
        Specification<Counselor> specification = (Specification<Counselor>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (counselor != null) {
                if (counselor.getCounselorId() != null && !counselor.getCounselorId().equals("")) {
                    list.add(criteriaBuilder.like(root.get("counselorId").as(String.class),"%" + counselor.getCounselorId() + "%"));
                }
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };
        return counselorRepository.findAll(specification,pageable);
    }
}
