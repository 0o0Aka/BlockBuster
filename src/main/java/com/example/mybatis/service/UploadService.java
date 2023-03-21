package com.example.mybatis.service;

import com.example.mybatis.mapper.UploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadService {
    @Autowired
    UploadMapper uploadMapper;
    public void changeImg(String imgname,int id) {
        uploadMapper.changeImg(imgname,id);
    }

    public void uploadImageAdmin(String originalFilename, String nickname) {
        uploadMapper.uploadImageAdmin(originalFilename,nickname);
    }

    public void uploadMovieImg(String originalFilename, String name) {
        uploadMapper.uploadMovieImg(originalFilename,name);
    }

    public void uploadMovie(String originalFilename, String name) {
        uploadMapper.uploadMovie(originalFilename,name);
    }

    public void uploadActorImg(String originalFilename, String name) {
        uploadMapper.uploadActorImg(originalFilename,name);
    }
}
