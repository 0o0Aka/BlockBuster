package com.example.mybatis.service;

import com.example.mybatis.bean.Movie;
import com.example.mybatis.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    MovieMapper movieMapper;
    public List<Movie> getTurnMovies(){
        return movieMapper.getTurnMovies();
    }

    public List<Movie> getRateMovies() {
        return movieMapper.getRateMovies();
    }

    public Movie getMovieById(int id) {
        return movieMapper.getMovieById(id);
    }

    public List<Movie> getRelateMovie(int id) {
        return movieMapper.getRelateMovie(id);
    }

    public List<Map> searchMovieByName(String name) {
        return movieMapper.searchMovieByName(name);
    }

    public List<Map> getMovieByCategory(String category) {
        if(category.equals("所有"))
            return movieMapper.getAllMovie();
        else{
            return movieMapper.getMovieByCategory(category);
        }

    }

    public void addClickrateById(int movieid) {
        movieMapper.addClickrateById(movieid);
    }

    public List<Movie> getAllMovieAdmin() {
        return movieMapper.getAllMovieAdmin();
    }

    public List<Movie> searchMovie(String id, String name) {
        if(id!=""){
            int id1=Integer.parseInt(id);
            return movieMapper.searchMovieById(id1);
        }
        else{
            return movieMapper.getMovieByName(name);
        }
    }

    public String updateMovieById(Movie movie) {
        movieMapper.updateMovieById(movie);
        return "更改电影信息成功";
    }

    public void deleteMovie(int id) {
        movieMapper.deleteMovie(id);
    }

    public void addMovie(Movie movie) {
        movieMapper.addMovie(movie);
    }

    public String getMovieUrl(int id) {
        return movieMapper.getMovieUrl(id);
    }
}
