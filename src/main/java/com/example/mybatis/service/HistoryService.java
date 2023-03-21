package com.example.mybatis.service;


import com.example.mybatis.bean.History;
import com.example.mybatis.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistoryService {
    @Autowired
    HistoryMapper historyMapper;
    public void addHistory(int userid, int movieid, double time) {
        int count=historyMapper.haveHistory(userid,movieid);
        if(time>3)
            time-=3;
        else
            time=0;
        if(count!=0){
            historyMapper.updateHistory(userid,movieid,time);
        }else{
            historyMapper.addHistory(userid,movieid,time);
        }

    }

    public double getHistoryById(int userid, int movieid) {
        int count=historyMapper.haveHistory(userid,movieid);
        if(count!=0){
            return historyMapper.getHistoryById(userid,movieid);
        }
        else
            return 0;
    }

    public List<Map> getAllHistory(int userid) {
        return historyMapper.getAllHistory(userid);
    }
}
