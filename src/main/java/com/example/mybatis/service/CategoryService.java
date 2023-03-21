package com.example.mybatis.service;

import com.example.mybatis.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    public List<String> getCategoryById(int id) {
        return categoryMapper.getCategoryById(id);
    }

    public void addCategory(String name, String category) {
        categoryMapper.addCategory(name,category);
    }

    public List<Map> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    public List<Map> searchCategoryByName(String name) {
        return categoryMapper.searchCategoryByName(name);
    }

    public String updateCategory(int id, String label) {

        String date[]=label.split(" ");
        categoryMapper.deleteCategoryByMovieId(id);
        for(int i=0;i<date.length;i++) {
            categoryMapper.updateCategory(id,date[i]);
        }
        return "电影类别更新成功";
    }
}
