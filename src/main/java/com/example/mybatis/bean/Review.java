package com.example.mybatis.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.crypto.Data;
import java.util.Date;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    int id;
    int userid;
    int movieid;
    String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    Date time;
    Double rate;
}
