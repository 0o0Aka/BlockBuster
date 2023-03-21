package com.example.mybatis.controller;

import com.example.mybatis.service.PerforrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Perform")
public class PerformController {
    @Autowired
    PerforrmService perforrmService;
    @RequestMapping("/getPerformMovieById")
    public List<Map> getPerformMovieById(int id){
        return perforrmService.getPerformMovieById(id);
    }
}
