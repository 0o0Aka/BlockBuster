package com.example.mybatis.mapper;

import com.example.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
   List<User> getAllUser();
   User getUserById(int id);
   int addUser(User user);
   int updataUser(User user);
   int deleteUser(int id);
   int findUserById(int id);
   List<Map> getAllUserSalary();
   int findUserByNickname(String nickname);

    List<User> getUserByNickname(String name);

   Integer userLogin(String username, String password);

    void changeUserName(int id,String name);

   int findUserByEmail(String email);

   void changeUserEmail(int id, String email);

   void changeUserPassword(int id, String password);

    void updateUserById(User user);

    boolean haveUserByNickname(String nickname,int id);

    boolean haveUserByEmail(String email,int id);

    List<User> getUserByEmail(String email);

    List<User> searchUser(String nickname, String email);
}
