package com.example.mybatis.controller;

import com.example.mybatis.bean.Complain;
import com.example.mybatis.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/Complain")
public class ComplainController {
    @Autowired
    ComplainService complainService;

    @RequestMapping("/addComplain")
    public void addComplain(int reviewid,String content,String type,int movieid){
        complainService.addComplain(reviewid,content,type,movieid);
    }

    @RequestMapping("getAllComplain")
    public Map<String, Object> getAllComplain(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",complainService.getAllComplain().size());
        map.put("data",complainService.getAllComplain());
        return map;
    }

    @RequestMapping("searchComplain")
    public Map<String, Object> searchComplain(String name,String type){
        System.out.println(name+type);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",complainService.searchComplain(name,type).size());
        map.put("data",complainService.searchComplain(name,type));
        return map;
    }

    @RequestMapping("/deleteComplain")
    public void deleteComplain(int id){
        complainService.deleteComplain(id);

    }
}
