package com.example.mybatis.mapper;

import com.example.mybatis.bean.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ReviewMapper {
    List<Map> getReviewById(int id);

    int getReviewCount(int id);

    //查询是否已经存在评论
    int haveReview(int userid,int movieid);

    void addReview(Review review);

    List<Map> getAllReview(int id);

    void deleteReview(int id);
}
