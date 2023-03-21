package com.example.mybatis.controller;

import com.example.mybatis.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="History")
public class HistoryController {
    @Autowired
    HistoryService historyService;
    @RequestMapping("/addHistory")
    public void addHistory(int userid,int movieid,double time){
        historyService.addHistory(userid,movieid,time);
    }

    @RequestMapping("/getHistoryById")
    public double getHistoryById(int userid,int movieid){
        return historyService.getHistoryById(userid,movieid);
    }

    @RequestMapping("/getAllHistory")
    public List<Map> getAllHistory(int userid){
        return historyService.getAllHistory(userid);
    }
}
