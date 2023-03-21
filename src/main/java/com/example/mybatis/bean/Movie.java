package com.example.mybatis.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    int id;
    String name;
    String category;
    float rate;
    String url;
    String img;
    int clickrate;
    String overview;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    Date releasedate;
    int length;

}
