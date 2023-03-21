package com.example.mybatis.mapper;

import com.example.mybatis.bean.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MovieMapper {
    public List<Movie> getTurnMovies();

    public List<Movie> getRateMovies();

    public Movie getMovieById(int id);

    List<Movie> getRelateMovie(int id);

    List<Map> searchMovieByName(String name);

    List<Map> getAllMovie();

    List<Map> getMovieByCategory(String category);

    void addClickrateById(int movieid);

    List<Movie> getAllMovieAdmin();

    List<Movie> getMovieByName(String name);

    List<Movie> searchMovieById(int id);

    void updateMovieById(Movie movie);

    void deleteMovie(int id);

    void addMovie(Movie movie);

    String getMovieUrl(int id);
}
