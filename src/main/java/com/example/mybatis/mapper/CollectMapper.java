package com.example.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CollectMapper {
    int isCollect(int userid, int movieid);

    void addCollect(int userid, int movieid);

    List<Map> getAllCollectById(int userid);

    List<Map> getAllCollectById1(int userid);

    void deleteCollectById(int userid,int movieid);
}
