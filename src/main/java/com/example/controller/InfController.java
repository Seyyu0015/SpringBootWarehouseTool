package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.InfService;
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

@Tag(name = "查询管理", description = "InfController")
@Controller
public class InfController {

    @Autowired
    private InfService infService;

    @Operation(summary = "查询用户列表")
    @GetMapping("/user")
    @ResponseBody
    public CommonResult selectUserList(String userid,String password){
        return infService.queryUserList(userid,password);
    }

}