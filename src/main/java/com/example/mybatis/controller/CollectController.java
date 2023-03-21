package com.example.mybatis.controller;

import com.example.mybatis.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/Collect")
public class CollectController {
    @Autowired
    CollectService collectService;

    @RequestMapping("/addCollect")
    public String addCollect(int userid, int movieid) {
        return collectService.addCollect(userid, movieid);
    }

    @RequestMapping("/getAllCollectById")
    public List<Map> getAllCollectById(int userid, int select) {
        System.out.println(userid+select);
        return collectService.getAllCollectById(userid, select);
    }

    @RequestMapping("/deleteCollectById")
    public void deleteCollectById(int userid,int movieid) {
        collectService.deleteCollectById(userid,movieid);
    }
}
