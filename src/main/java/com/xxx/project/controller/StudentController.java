package com.xxx.project.controller;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Student;
import com.xxx.project.repository.StudentRepository;
import com.xxx.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(allowCredentials = "true")
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/get/{id}")
    private Student getStudent(@PathVariable(value = "id") String id){
        return studentRepository.findById(id).get();
    }
    @PostMapping(value = "/add")
    public Student add(@RequestBody Student student) throws MsgException {
        return studentService.add(student);
    }
    @DeleteMapping(value = "/delete")
    public Integer delete(@RequestBody List<String> studentIds) {
        return studentService.delete(studentIds);
    }
    @PutMapping(value = "/update")
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }
    @PostMapping(value = "/page")
    public Page<Student> page(@RequestBody PageArgs args) {
        Pageable pageable = PageRequest.of(args.getCurrentPage(), args.getPageSize(), Sort.by(Sort.Direction.DESC, "createDate"));
        return studentService.pageStudent(pageable,args.getStudent());
    }
    @GetMapping("/all")
    public List<Student> all(){
        return studentRepository.findAll();
    }
}
