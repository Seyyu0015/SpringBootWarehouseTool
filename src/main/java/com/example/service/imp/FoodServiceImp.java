package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.FoodMapper;
import com.example.pojo.Food;
import com.example.service.FoodService;
import com.example.service.FoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@Service
public class FoodServiceImp implements FoodService {

    @Resource
    private FoodMapper foodMapper;

    @Override
    public CommonResult queryFoodList() {
        List<Food> foodList = foodMapper.selectFood();

        if(foodList==null||foodList.isEmpty()){
            return CommonResult.fail(500,"查询数据信息为空");
        }

        HashMap result = new HashMap();

        for (Food food : foodList) {
            result.put(food.getFood(),food.getValue());
        }

        return CommonResult.success(result);
    }

    @Override
    public CommonResult addFood(String food,double value) {
        try{
            foodMapper.addFood(food, value);
            return CommonResult.success("插入成功：" + food);
        }catch(Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    //删除食物
    @Override
    public CommonResult delFood(String food) {
        try{
            foodMapper.delFood(food);
            return CommonResult.success("删除"+food+"成功");
        }catch(Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    //修改价格
    @Override
    public CommonResult setFoodValue(String food, double value) {
        try{
            foodMapper.setFoodValue(food,value);
            return CommonResult.success("修改"+food+"价格成功");
        }catch(Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }
}
