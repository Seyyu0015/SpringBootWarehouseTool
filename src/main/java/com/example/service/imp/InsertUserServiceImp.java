package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.FoodMapper;
import com.example.mapper.InsertUserMapper;
import com.example.pojo.Food;
import com.example.service.FoodService;
import com.example.service.InsertUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-13
 */
@Service
public class InsertUserServiceImp implements InsertUserService {

    @Resource
    private InsertUserMapper insertUserMapper;

    @Override
    public CommonResult addUser(String username) {
    System.out.println("Service:" + username);
    insertUserMapper.addUser(username);
    return CommonResult.success("插入成功：" + username);

    }
}
