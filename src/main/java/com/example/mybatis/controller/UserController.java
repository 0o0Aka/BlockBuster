package com.example.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.example.mybatis.bean.User;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="User")
public class UserController {

    //    @Autowired
//    UserMapper userMapper;
//    @RequestMapping("/getAllUser")
//    public List<User> getAllUser(){
//       List<User> userList=userMapper.getAllUser();
//       for(User user : userList)
//           System.out.println(user);
//       return  userList;
//    }
//    @PostMapping("/getUserById")
//    public User getUserById(@RequestBody String id){
//        Integer realid = Integer.parseInt(JSON.parseObject(id).get("id").toString());
//        System.out.println(realid);
//        User user=userMapper.getUserById(realid);
//        System.out.println("id="+realid);
//        return user;
//    }
//    @RequestMapping("/addUser")
//    public String addUser(User user){
//        int count=userMapper.findUserById(user.getId());
//        if(count>0)
//            return "id已存在，请重新输入";
//        else {
//            userMapper.addUser(user);
//            return "ok";
//        }
//
//    }
//    @RequestMapping("/updataUser")
//    public String updataUser(){
//        userMapper.updataUser(new User(1,"宇晨龚",12,"男"));
//        return "ok";
//    }
//    @RequestMapping("/deleteUser")
//    public String deleteUser(int id){
//        userMapper.deleteUser(id);
//        return "ok";
//    }
//    @RequestMapping("/getAllUserSalary")
//    public List<Map> getAllUserSalary(){
//        System.out.println(userMapper.getAllUserSalary());
//        return userMapper.getAllUserSalary();
//    }
    @Autowired
    UserService userService;
    @RequestMapping("/addUser")
    public int addUser(User user) {
        return userService.addUser(user);
    }
    @PostMapping("/userLogin")
    public int userLogin(String username, String password){
        int id=userService.userLogin(username,password);
        System.out.println(id);
        return id;
    }
    @RequestMapping("/getUserById")
    public User getUserById(int id){
        return userService.getUserById(id);
    }

    @RequestMapping("/changeUserName")
    public String changeUserName(int id,String name){
       return userService.changeUserName(id,name);
    }

    @RequestMapping("/changeUserEmail")
    public String changeUserEmail(int id,String email){
        return userService.changeUserEmail(id,email);
    }

    @RequestMapping("/changeUserPassword")
    public String changeUserPassword(int id,String password){
        return userService.changeUserPassword(id,password);
    }

    @RequestMapping("/getAllUser")
    public Map<String, Object> gteAlluser(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",userService.getAlluser().size());
        map.put("data",userService.getAlluser());
        return map;
    }

    @RequestMapping("/updateUserById")
    public String updateUserById (User user){
        return userService.updateUserById(user);
    }
    @RequestMapping("/deleteUser")
    public void deleteUser(int id){
        userService.deleteUser(id);
    }

    @RequestMapping("searchUser")
    public Map<String, Object> searchUser(String nickname,String email){
        System.out.println(nickname+email);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",userService.searchUser(nickname,email).size());
        map.put("data",userService.searchUser(nickname,email));
        return map;
    }
}
