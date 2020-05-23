package com.xxx.project.serviceImpl;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.entity.Comment;
import com.xxx.project.repository.CommentRepository;
import com.xxx.project.service.CommentService;
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
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    // 新增一条评论信息
    @Override
    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    // 删除一条评论信息
    @Override
    public void delete(String id) {
        commentRepository.deleteById(id);
    }

    // 更新一条评论信息
    @Override
    public Comment update(Comment comment) {
        comment.setModifyDate(Calendar.getInstance().getTime());
        return commentRepository.save(comment);
    }

    // 通过竞赛id 评论信息分页查询，可实现无限滚动
    @Override
    public Page<Comment> page(PageArgs pageArgs, String id) {
        Pageable pageable = PageRequest.of(pageArgs.getCurrentPage(), pageArgs.getPageSize(), Sort.by(Sort.Direction.DESC, "createDate"));
        Specification<Comment> specification = (Specification<Comment>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("competitionId").as(String.class), id));
            list.add(criteriaBuilder.equal(root.get("parentId").as(String.class), "-1"));
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };
        return commentRepository.findAll(specification, pageable);
    }

    @Override
    public List<Comment> findByParentId(String parentId) {
        return commentRepository.findByParentId(parentId);
    }
}
