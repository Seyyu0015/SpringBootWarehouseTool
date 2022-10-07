package com.example.service;

import com.example.common.CommonResult;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
public interface FoodService {
    CommonResult queryFoodList();
    CommonResult addFood(String food,double value);
    CommonResult delFood(String food);
    CommonResult setFoodValue(String food, double value);
    CommonResult setFoodEater(String food,
                              int user1,
                              int user2,
                              int user3,
                              int user4,
                              int user5);

}


