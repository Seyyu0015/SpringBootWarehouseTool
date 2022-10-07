package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.UserService;
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

@Tag(name = "用户接口", description = "UserController")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "查询用户列表")
    @GetMapping("/user")
    @ResponseBody
    public CommonResult selectUserList(){
        return userService.queryUserList();
    }

    @Operation(summary = "新增用户")
    @GetMapping("/addUser")
    @ResponseBody
    public CommonResult addUser(String username){
        return userService.addUser(username);
    }

    @Operation(summary = "删除用户")
    @GetMapping("/delUser")
    @ResponseBody
    public CommonResult delUser(String username){
        return userService.delUser(username);
    }
}
