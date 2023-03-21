package com.example.mybatis.service;

import com.example.mybatis.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CollectService {
    @Autowired
    CollectMapper collectMapper;
    public String addCollect(int userid, int movieid) {
        int count=collectMapper.isCollect(userid,movieid);
        if(count!=0){
            return "您已收藏过该电影";
        }else {
            collectMapper.addCollect(userid,movieid);
            return "收藏电影成功";
        }
    }

    public List<Map> getAllCollectById(int userid,int select) {
        if(select==1)
        return collectMapper.getAllCollectById(userid);
        else
            return collectMapper.getAllCollectById1(userid);
    }

    public void deleteCollectById(int userid,int movieid) {
        collectMapper.deleteCollectById(userid,movieid);


    }
}
