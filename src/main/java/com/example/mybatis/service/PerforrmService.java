package com.example.mybatis.service;

import com.example.mybatis.mapper.PerformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PerforrmService {
    @Autowired
    PerformMapper performMapper;

    public List<Map> getPerformMovieById(int id) {
        return performMapper.getPerformMovieById(id);
    }
}
