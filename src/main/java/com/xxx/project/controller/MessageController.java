package com.xxx.project.controller;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.entity.Counselor;
import com.xxx.project.entity.Message;
import com.xxx.project.entity.Student;
import com.xxx.project.entity.User;
import com.xxx.project.repository.CounselorRepository;
import com.xxx.project.repository.MessageRepository;
import com.xxx.project.repository.StudentRepository;
import com.xxx.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(allowCredentials = "true")
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CounselorRepository counselorRepository;

    @PostMapping(value = "/add")
    @Transactional
    public Message add(@RequestBody Message message) {
        message.getUserIds().forEach(id -> {
            Message message1 = new Message();
            Optional<Student> student = studentRepository.findById(id);
            Optional<Counselor> counselor = counselorRepository.findById(id);
            Optional<User> tempUser = userRepository.findById(id);
            if (student.isPresent()) {
                User user = userRepository.findByName(student.get().getStudentId());
                message1.setUserId(user.getId());
                message1.setName(student.get().getName());
            }
            if (counselor.isPresent()) {
                User user = userRepository.findByName(counselor.get().getCounselorId());
                message1.setUserId(user.getId());
                message1.setName(counselor.get().getName());
            }
            if (tempUser.isPresent()) {
                message1.setUserId(tempUser.get().getId());
                message1.setName(tempUser.get().getName());
            }
            message1.setCreateUserId(message.getCreateUserId());
            message1.setContent(message.getContent());
            message1.setStatus(0);
            messageRepository.save(message1);
        });
        return null;
    }
    // 查询发出的站内消息
    @PostMapping(value = "/send/{userId}")
    public Page<Message> getSendMessage(@PathVariable(value = "userId") String userId, @RequestBody PageArgs args) {
        Pageable pageable = PageRequest.of(args.getCurrentPage(), args.getPageSize(), Sort.by(Sort.Direction.DESC, "createDate"));
        Specification<Message> specification = (Specification<Message>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("createUserId").as(String.class), userId));
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };
        return messageRepository.findAll(specification,pageable);
    }
    @DeleteMapping(value = "/delete/{messageId}")
    public void deleteMessage(@PathVariable(value = "messageId") String id){
        messageRepository.deleteById(id);
    }
    @GetMapping(value = "/{id}/{status}")
    public List<Message> getList(@PathVariable(value = "id") String id,@PathVariable(value = "status") Integer status){
        return messageRepository.findAllByUserIdAndStatus(id,status);
    }
    // 改变信息状态
    @PutMapping(value = "/{id}/{status}")
    public void changeStatus(@PathVariable(value = "id") String messageId,@PathVariable(value = "status") Integer status){
        Message message = messageRepository.findById(messageId).get();
        message.setStatus(status);
        messageRepository.save(message);
    }
    // 全部标记为已读
    @GetMapping(value = "/read/{userId}")
    public void read(@PathVariable(value = "userId") String userId) {
        messageRepository.read(userId);
    }

    // 全部删除
    @GetMapping(value = "/delete/{userId}")
    public void delete(@PathVariable(value = "userId") String userId) {
        messageRepository.alldelete(userId);
    }

    // 清空回收站
    @GetMapping(value = "/clear/{userId}")
    public void clear(@PathVariable(value = "userId") String userId) {
        messageRepository.clearMessage(userId);
    }
}
