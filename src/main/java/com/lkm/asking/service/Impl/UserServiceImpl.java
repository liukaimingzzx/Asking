package com.lkm.asking.service.Impl;

import com.lkm.asking.dao.UserDao;
import com.lkm.asking.entity.User;
import com.lkm.asking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int insertUser(User user) {
        int flag = userDao.insertUser(user);
        if(flag>0){
            return 1;
        }else
            return 0;
    }

    @Override
    public User queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }

    @Override
    public User userLogin(String username, String password) {
        return userDao.userLogin(username,password);
    }

    @Override
    public User updateUser(User user) {
        int flag = userDao.updateUser(user);
        if(flag>0){
            return userDao.queryByUsername(user.getUsername());
        }else{
            throw new RuntimeException("更新失败!");
        }

    }

    @Override
    public int updateAvater(String username, String avater) {
        int flag = userDao.updateAvater(username,avater);
        if(flag>0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void updateQueCount(String username) {
        userDao.updateQueCount(username);
    }

    @Override
    public void updateAnsCount(String username) {
        userDao.updateAnsCount(username);
    }

    @Override
    public void checkin(String username,String date) {
        userDao.checkin(username,date);

    }

    @Override
    public String queryTime(String username) {
        return userDao.queryTime(username);
    }

    @Override
    public String queryNickname(String username) {
        return userDao.queryNickname(username);
    }

    @Override
    public String queryAvater(String username) {
        return userDao.queryAvater(username);
    }

    @Override
    public void addCoin(String username) {
        userDao.addCoin(username);
    }

    @Override
    public void decCoin(String username) {
        userDao.decCoin(username);
    }

    @Override
    public void updateInfo(Integer queCount, Integer ansCount,String username) {
        userDao.updateInfo(queCount,ansCount,username);
    }

    @Override
    public String queryPassword(String username) {
        return userDao.queryPassword(username);
    }

    @Override
    public Integer queryCoin(String username) {
        return userDao.queryCoin(username);
    }
}
