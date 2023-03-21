package com.example.mybatis.mapper;

import com.example.mybatis.bean.Complain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ComplainMapper {
    void addComplain(int reviewid,String content,String type,int movieid);

    List<Map> getAllComplain();

    List<Map> searchComplain(String name, String type);

    List<Map> searchComplainByName(String name);

    List<Map> searchComplainByType(String name);

    void deleteComplain(int id);
}
