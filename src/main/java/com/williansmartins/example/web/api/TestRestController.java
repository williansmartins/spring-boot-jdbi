package com.williansmartins.example.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williansmartins.example.db.UserBean;
import com.williansmartins.example.service.UserService;

import java.util.List;

@RestController
public class TestRestController {

    private static int i;

    @Autowired
    UserService userService;
    @GetMapping("/api/user/list")
    public List<UserBean> list() {
        return userService.list();
    }

    @GetMapping("/api/user/insertOK")
    public Integer insertOK() {
        return userService.insertOK();
    }

    @GetMapping("/api/user/insertNotOK")
    public String insertNotOK() {
        userService.insertAndFail();
        return "ok";
    }

}
