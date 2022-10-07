package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.FoodService;
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

@Tag(name = "食材接口", description = "CalculatorController")
@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Operation(summary = "获取食材列表")
    @GetMapping("/food")
    @ResponseBody
    public CommonResult selectFoodList(){
        return foodService.queryFoodList();
    }

    @Operation(summary = "新增食材")
    @GetMapping("/addFood")
    @ResponseBody
    public CommonResult addFood(String food,double value) {
        return foodService.addFood(food,value);
    }

    @Operation(summary = "删除食材")
    @GetMapping("/delFood")
    @ResponseBody
    public CommonResult delFood(String food) {
        return foodService.delFood(food);
    }

    @Operation(summary = "改变食材价格")
    @GetMapping("/setFoodValue")
    @ResponseBody
    public CommonResult setFood(String food,double value) {
        return foodService.setFoodValue(food,value);
    }

}
