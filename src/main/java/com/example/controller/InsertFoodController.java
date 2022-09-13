package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.InsertFoodService;
import com.example.service.InsertUserService;
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
public class InsertFoodController {

    @Autowired
    private InsertFoodService insertFoodService;

    @GetMapping("/addFood")
    @ResponseBody
    public CommonResult addFood(String food,double value) {
        return insertFoodService.addFood(food,value);
    }
}
