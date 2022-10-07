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
}


