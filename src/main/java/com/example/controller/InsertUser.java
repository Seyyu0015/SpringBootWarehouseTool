package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.FoodService;
import com.example.service.InsertUserService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-13
 */

@Controller
public class InsertUser {

    @Autowired
    private InsertUserService insertUserService;

    @GetMapping("/addUser")
    @ResponseBody
    public CommonResult addUser(String username){
        return insertUserService.addUser(username);
    }
}
