package com.example.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UploadMapper {
    void changeImg(String imgname,int id);

    void uploadImageAdmin(String originalFilename, String nickname);

    void uploadMovieImg(String originalFilename, String name);

    void uploadMovie(String originalFilename, String name);

    void uploadActorImg(String originalFilename, String name);
}
