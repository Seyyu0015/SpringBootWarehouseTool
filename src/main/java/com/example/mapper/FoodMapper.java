package com.example.mapper;

import com.example.pojo.Food;
import com.example.pojo.User;

import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
public interface FoodMapper {
    List<Food> selectFood();
    void addFood(String food,double value);
    void delFood(String food);
}
