package com.example.mybatis.service;

import com.example.mybatis.bean.Review;
import com.example.mybatis.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    public List<Map> getReviewById(int id){
        return reviewMapper.getReviewById(id);
    }

    public int getReviewCount(int id) {
        return reviewMapper.getReviewCount(id);
    }


    public String addReview(Review review) {
        int count=reviewMapper.haveReview(review.getUserid(),review.getMovieid());
        if(count!=0){
            return "您已评论此电影，请勿重复评论";
        }
        else {
            reviewMapper.addReview(review);
            return "评论成功";
        }
    }

    public List<Map> getAllReview(int id) {
        return reviewMapper.getAllReview(id);
    }

    public void deleteReview(int id) {
        reviewMapper.deleteReview(id);

    }
}


