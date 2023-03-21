package com.example.mybatis.controller;

import com.example.mybatis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Category")
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("/getCategoryById")
    public List<String> getCategoryById(int id){
        System.out.println(categoryService.getCategoryById(id));
        return categoryService.getCategoryById(id);
    }

    @RequestMapping("/addCategory")
    public void addCategory(String name,String category){
        System.out.println(name+category);
        categoryService.addCategory(name,category);
    }

    @RequestMapping("/getAllCategory")
    public Map<String, Object> getAllCategory(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",categoryService.getAllCategory().size());
        map.put("data",categoryService.getAllCategory());
        return map;
    }

    @RequestMapping("/searchCategoryByName")
    public Map<String, Object> searchCategoryByName(String name){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",categoryService.searchCategoryByName(name).size());
        map.put("data",categoryService.searchCategoryByName(name));
        return map;
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(int id,String label){
        return categoryService.updateCategory(id,label);
    }
}
