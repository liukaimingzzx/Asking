package com.lkm.asking.dao;

import com.lkm.asking.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    int insertUser(User user);
    User queryByUsername(String username);
    User userLogin(String username,String password);
    int updateUser(User user);
    int updateAvater(String username,String avater);
    void checkin(String username,String date);
    void updateQueCount(String username);
    void updateAnsCount(String username);
    String queryTime(String username);
    String queryNickname(String username);
    String queryAvater(String username);
    String queryPassword(String username);
    void addCoin(String username);
    void decCoin(String username);
    void updateInfo(Integer queCount,Integer ansCount,String username);
    Integer queryCoin(String username);
    List<User> listAll();
    int deleteUser(String username);



}
