package com.example.mybatis.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    int id;
    int userid;
    int movieid;
    double time;
}
