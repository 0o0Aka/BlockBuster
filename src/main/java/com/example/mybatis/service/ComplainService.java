package com.example.mybatis.service;

import com.example.mybatis.bean.Complain;
import com.example.mybatis.mapper.ComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComplainService {
    @Autowired
    ComplainMapper complainMapper;

    public void addComplain(int reviewid,String content,String type,int movieid) {
        complainMapper.addComplain(reviewid,content,type,movieid);
    }

    public List<Map> getAllComplain() {
        return complainMapper.getAllComplain();
    }

    public List<Map> searchComplain(String name, String type) {
        if(name!=""&&type!="")
            return complainMapper.searchComplain(name,type);
        else if(name!="")
            return complainMapper.searchComplainByName(name);
        else{
            return complainMapper.searchComplainByType(type);
        }
    }

    public void deleteComplain(int id) {
        complainMapper.deleteComplain(id);
    }
}
