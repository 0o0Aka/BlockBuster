package com.example.mybatis.controller;

import com.example.mybatis.bean.Movie;
import com.example.mybatis.bean.User;
import com.example.mybatis.mapper.MovieMapper;
import com.example.mybatis.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @RequestMapping("/getTurnMovies")
    public List<Movie> getTurnMovies(){
        return  movieService.getTurnMovies();
    }
    @RequestMapping("/getRateMovies")
    public List<Movie> getRateMovies(){
        List<Movie> movieList=movieService.getRateMovies();
        return  movieList;
    }
    @RequestMapping("/getMovieById")
    public Movie getMovieById(int id){
        return movieService.getMovieById(id);
    }

    @RequestMapping("/getRelateMovie")
    public List<Movie> getRelateMovie(int id){
        return movieService.getRelateMovie(id);
    }

    @RequestMapping("/searchMovieByName")
    public List<Map> searchMovieByName(String name){
        return movieService.searchMovieByName(name);
    }

    @RequestMapping("getMovieByCategory")
    public List<Map> getMovieByCategory(String category){
        return movieService.getMovieByCategory(category);
    }

    @RequestMapping("/addClickrateById")
    public void addClickrateById(int movieid){
      movieService.addClickrateById(movieid);
    }

    @RequestMapping("/getAllMovieAdmin")
    public Map<String, Object> getAllMovieAdmin(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",movieService.getAllMovieAdmin().size());
        map.put("data",movieService.getAllMovieAdmin());
        return map;
    }

    @RequestMapping("/searchMovie")
    public Map<String, Object> searchMovie(String id,String name){
        System.out.println(id+name);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",movieService.searchMovie(id,name).size());
        map.put("data",movieService.searchMovie(id,name));
        return map;
    }

    @RequestMapping("/updateMovieById")
    public String updateMovieById(Movie movie){
        return movieService.updateMovieById(movie);
    }

    @RequestMapping("/deleteMovie")
    public void deleteMovie(int id){
        movieService.deleteMovie(id);
    }

    @RequestMapping("/addMovie")
    public String addMovie(Movie movie){
        System.out.println(movie);
        movieService.addMovie(movie);
        return "添加电影成功";
    }

    @RequestMapping("/getMovieUrl")
    public String getMovieUrl(int id){
        return movieService.getMovieUrl(id);
    }

}
