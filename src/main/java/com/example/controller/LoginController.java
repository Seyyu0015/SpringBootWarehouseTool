package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */

@Tag(name = "登录", description = "LoginController")
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    @ResponseBody
    public CommonResult login(String userid,String password) {
        return loginService.login(userid,password);
    }

}
