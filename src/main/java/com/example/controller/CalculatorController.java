package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.CalculatorService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/index")
    @ResponseBody
    public CommonResult selectUserList(){
        return calculatorService.calculator();
    }
}
