package com.example.mybatis.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HistoryMapper {
    void addHistory(int userid, int movieid, double time);

    double getHistoryById(int userid, int movieid);

    int haveHistory(int userid, int movieid);

    void updateHistory(int userid, int movieid, double time);

    List<Map> getAllHistory(int userid);
}
