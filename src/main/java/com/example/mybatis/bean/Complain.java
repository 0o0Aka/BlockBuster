package com.example.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Complain {
    int id;
    int reviewid;
    String content;
    String type;
    int movieid;
}
