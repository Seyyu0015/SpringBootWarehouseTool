package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    public void truncateUser(){
        userMapper.truncateUser();//清空用户表
    };

    @Override
    public CommonResult queryUserList() {
        List<User> userList = userMapper.selectUser();

        if(userList==null||userList.isEmpty()){
            return CommonResult.fail(500,"查询数据信息为空");
        }
        List<String> result=new ArrayList<>();

        for (User user : userList) {
            result.add(user.getUsername());
        }
        return CommonResult.success(result);
    }

    @Override
    public CommonResult addUser(String username) {
        //获得数据
        List<User> userList = userMapper.selectUser();

        //判断人数是否已满
        if(userList.size()>=5){
            return CommonResult.fail(500,"目前最多支持五人！");
        }

        //清空数据表
        userMapper.truncateUser();

        //重新插入数据
        User newUser = new User(userList.size()+1,username);
        userList.add(newUser);

        try {
            int i = 1;
            for (User user : userList) {
                userMapper.addUser(i,user.getUsername());
                i++;
            }
            return CommonResult.success("插入成功：" + username);
        } catch(Exception e) {
            return CommonResult.fail(500, String.valueOf(e));
        }


    }

    @Override
    public CommonResult delUser(String username) {
        try {
            userMapper.delUser(username);
            return CommonResult.success("删除"+username+"成功！");
        }catch (Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }
}
