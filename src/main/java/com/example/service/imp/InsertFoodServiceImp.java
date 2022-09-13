package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.InsertFoodMapper;
import com.example.mapper.InsertUserMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.InsertFoodService;
import com.example.service.InsertUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-13
 */
@Service
public class InsertFoodServiceImp implements InsertFoodService {

    @Resource
    private InsertFoodMapper insertFoodMapper;

    @Override
    public CommonResult addFood(String food,double value) {
        try{
            insertFoodMapper.addFood(food, value);
            return CommonResult.success("插入成功：" + food);
        }catch(Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }



    }
}
