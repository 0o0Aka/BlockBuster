package com.example.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CategoryMapper {
    List<String> getCategoryById(int id);

    void addCategory(String name, String category);

    List<Map> getAllCategory();

    List<Map> searchCategoryByName(String name);

    void deleteCategoryByMovieId(int id);

    void updateCategory(int id, String s);
}
