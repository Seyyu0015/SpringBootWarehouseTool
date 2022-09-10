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
//        List<String> foods=new ArrayList<>();
//        List<Float> values=new ArrayList<>();
//        List<List<?>> result=new ArrayList<>();

        for (Food food : foodList) {
            result.put(food.getFood(),food.getValue());
//            foods.add(food.getFood());
//            values.add(food.getValue());
        }

//        result.add(foods);
//        result.add(values);

        return CommonResult.success(result);
    }
}
