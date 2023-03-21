package com.example.mybatis.service;

import com.example.mybatis.bean.User;
import com.example.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int getUserByName(String name) {
        int count=userMapper.findUserByNickname(name);
        return count;
    }

    public int userLogin(String username, String password) {
        if(userMapper.userLogin(username,password)!=null){
            return userMapper.userLogin(username,password);
        }else
            return 0;

    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public int addUser(User user) {
        int count=userMapper.findUserByNickname(user.getNickname());//用户名是否存在
        int count1=userMapper.findUserByEmail(user.getEmail());//邮箱是否存在
        if(count!=0){
            return 1;
        }else if(count1!=0){
            return 2;
        }
        else{
            userMapper.addUser(user);
            return 0;
        }
    }

    public String changeUserName(int id, String name) {
        int count=userMapper.findUserByNickname(name);
        if(count!=0){
            return "用户名已存在，请重新输入";
        }
        else {
            userMapper.changeUserName(id,name);
            return "用户名修改成功";
        }
    }

    public String changeUserEmail(int id, String email) {
        int count=userMapper.findUserByEmail(email);
        if(count!=0){
            return "邮箱已存在，请重新输入";
        }
        else {
            userMapper.changeUserEmail(id,email);
            return "邮箱修改成功";
        }
    }

    public String changeUserPassword(int id, String password) {
        userMapper.changeUserPassword(id,password);
        return "密码修改成功";
    }

    public List<User> getAlluser() {
        return userMapper.getAllUser();
    }

    public String updateUserById(User user) {
        if(userMapper.haveUserByNickname(user.getNickname(),user.getId())){
            return "用户名已存在";
        }else if(userMapper.haveUserByEmail(user.getEmail(),user.getId())){
            return "邮箱已存在";
        }
        else{
            userMapper.updateUserById(user);
            return "更新用户信息成功";
        }
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public List<User> searchUser(String nickname, String email) {
        if(nickname!=""&&email!=""){
            return userMapper.searchUser(nickname,email);
        }
        else if(nickname!=""){
            return userMapper.getUserByNickname(nickname);
        }
        else {
            return userMapper.getUserByEmail(email);
        }
    }
}

