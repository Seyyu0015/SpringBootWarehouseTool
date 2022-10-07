package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
    @Parameters({
            @Parameter(name = "food", description = "食材", example = "韭菜"),
            @Parameter(name = "value", description = "价格", example = "2.5")
    })
    @GetMapping("/addFood")
    @ResponseBody
    public CommonResult addFood(String food,double value) {
        return foodService.addFood(food,value);
    }

    @Operation(summary = "删除食材")
    @Parameter(name = "food", description = "食材", example = "韭菜")
    @GetMapping("/delFood")
    @ResponseBody
    public CommonResult delFood(String food) {
        return foodService.delFood(food);
    }

    @Operation(summary = "改变食材价格")
    @Parameters({
            @Parameter(name = "food", description = "食材", example = "韭菜"),
            @Parameter(name = "value", description = "价格", example = "2.5")
    })
    @GetMapping("/setFoodValue")
    @ResponseBody
    public CommonResult setFoodValue(String food,double value) {
        return foodService.setFoodValue(food,value);
    }

    @Operation(summary = "统计食材吃过的用户")
    @Parameters({
    @Parameter(name = "food",description = "食材",example = "韭菜"),
    @Parameter(name = "user1",description = "1号用户是否吃过",example = "1"),
    @Parameter(name = "user2",description = "2号用户是否吃过",example = "0"),
    @Parameter(name = "user3",description = "3号用户是否吃过",example = "0"),
    @Parameter(name = "user4",description = "4号用户是否吃过",example = "1"),
    @Parameter(name = "user5",description = "5号用户是否吃过",example = "1")
    })
    @GetMapping("/setFoodEater")
    @ResponseBody
    public CommonResult setFoodEater(String food,
                                     int user1,
                                     int user2,
                                     int user3,
                                     int user4,
                                     int user5
                                     ) {
        return foodService.setFoodEater(food,user1,user2,user3,user4,user5);
    }

}
