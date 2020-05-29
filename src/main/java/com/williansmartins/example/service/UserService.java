package com.williansmartins.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.williansmartins.example.db.UserBean;
import com.williansmartins.example.db.UserDao;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public List<UserBean> list(){
        System.out.println(userDao.list());
        return userDao.list();
    }

    @Transactional
    public Integer insertOK(){
        UserBean test = new UserBean();
        test.setUsername("username"+new Date().getTime());
        return userDao.insert(test);
    }

    @Transactional
    public void insertAndFail(){
        UserBean test = new UserBean();
        test.setUsername("username"+new Date().getTime());
        userDao.insert(test);

        throw new RuntimeException("Hello this is an error message");
    }

}
