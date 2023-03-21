package com.example.mybatis.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.mybatis.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.sun.xml.internal.fastinfoset.util.DuplicateAttributeVerifier.MAP_SIZE;

@RestController
@RequestMapping(value = "/api")
public class UploadController {
    @Autowired
    UploadService uploadService;
    public final static String UPLOAD_FILE_PATH = "C:\\Users\\Study\\毕设\\BlockBuster-前端\\BlockBuster_User\\img\\";
    public final static String UPLOAD_FILE_PATH1 = "C:\\Users\\Study\\毕设\\BlockBuster-前端\\BlockBuster_User\\mp4\\";

    @RequestMapping(value = "/uploads")
    public String uploadImage(@RequestParam("file") MultipartFile file,int id) {
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(MAP_SIZE);
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(UPLOAD_FILE_PATH, file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            uploadService.changeImg(file.getOriginalFilename(),id);
            return JSONObject.toJSONString(resObj);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/uploadsAdmin")
    public String uploadImageAdmin(@RequestParam("file") MultipartFile file,String nickname) {
        System.out.println(nickname);
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(MAP_SIZE);
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(UPLOAD_FILE_PATH, file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            uploadService.uploadImageAdmin(file.getOriginalFilename(),nickname);
            return JSONObject.toJSONString(resObj);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/uploadMovieImg")
    public String uploadMovieImg(@RequestParam("file") MultipartFile file,String name) {
        System.out.println(name+"img");
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(MAP_SIZE);
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(UPLOAD_FILE_PATH, file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            uploadService.uploadMovieImg(file.getOriginalFilename(),name);
            return JSONObject.toJSONString(resObj);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/uploadMovie")
    public String uploadMovie(@RequestParam("file") MultipartFile file,String name) {
        System.out.println(name);
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(MAP_SIZE);
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(UPLOAD_FILE_PATH1, file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            uploadService.uploadMovie(file.getOriginalFilename(),name);
            return JSONObject.toJSONString(resObj);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/uploadActorImg")
    public String uploadActorImg(@RequestParam("file") MultipartFile file,String name) {
        System.out.println(name+"img");
        System.out.println(UPLOAD_FILE_PATH);
        System.out.println(file.getOriginalFilename());
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(MAP_SIZE);
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(UPLOAD_FILE_PATH, file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            uploadService.uploadActorImg(file.getOriginalFilename(),name);
            return JSONObject.toJSONString(resObj);
        } else {
            return null;
        }
    }
}
