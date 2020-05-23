package com.xxx.project.controller;

import com.xxx.project.bean.PageArgs;
import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Review;
import com.xxx.project.repository.ReviewRepository;
import com.xxx.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewRepository reviewRepository;

    //@GetMapping(value = "/get/{id}")
   // private Review getStudent(@PathVariable(value = "id") String id){ return reviewService.findById(id).get(); }
    @PostMapping(value = "/add")
    public Review add(@RequestBody Review review) throws MsgException {
        return reviewService.add(review);
    }
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        reviewService.delete(id);
    }
    @PutMapping(value = "/update")
    public Review update(@RequestBody Review review) {
        return reviewService.update(review);
    }
    @PostMapping(value = "/page")
    public Page<Review> page(@RequestBody PageArgs args) {
        return reviewService.page(args);
    }


}
