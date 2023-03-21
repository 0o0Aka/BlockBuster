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
public class Actor {
    int id;
    String cname;
    String ename;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    Date birth;
    String country;
//    int movieid;
    String info;
    int sex;
    String img;
//    String play;
//    int role;//0:导演 1:演员 2:编剧
}
