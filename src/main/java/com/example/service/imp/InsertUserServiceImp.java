package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.FoodMapper;
import com.example.mapper.InsertUserMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Food;
import com.example.pojo.User;
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

    @Resource
    private UserMapper userMapper;

    @Override
    public CommonResult addUser(String username) {
        //判断人数是否已满
        List<User> userList = userMapper.selectUser();
        if(userList.size()>=5){
            return CommonResult.fail(500,"目前最多支持五人！");
        }

        //返回最后一名的ID+1 保证主键连贯
        User lastUser = userList.get(userList.size() - 1);
        int id =  lastUser.getId() + 1;

    insertUserMapper.addUser(id,username);
    return CommonResult.success("插入成功：" + username);

    }
}
