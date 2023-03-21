package com.example.mybatis.controller;

import com.example.mybatis.bean.Movie;
import com.example.mybatis.bean.Review;
import com.example.mybatis.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @RequestMapping("/getReviewById")
    public List<Map> getReviewById(int id){
        List<Map> reviewList=reviewService.getReviewById(id);
        for(Map map : reviewList)
            System.out.println(map);
        return reviewService.getReviewById(id);
    }
    @RequestMapping("/getReviewCount")
    public int getReviewCount(int id){
        return reviewService.getReviewCount(id);
    }
    @PostMapping("/addReview")
    public String  addReview(Review review){
        return reviewService.addReview(review);
    }

    @RequestMapping("/getAllReview")
    public List<Map> getAllReview(int id){
        return reviewService.getAllReview(id);
    }

    @RequestMapping("/deleteReview")
    public  void deleteReview(int id){
        reviewService.deleteReview(id);
        System.out.println("删除评论");
    }

}
