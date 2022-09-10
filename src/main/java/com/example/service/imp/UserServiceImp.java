package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public CommonResult queryUserList() {
        List<User> userList = userMapper.selectUser();

        if(userList==null||userList.isEmpty()){
            return CommonResult.fail(500,"查询数据信息为空");
        }
        List<String> result=new ArrayList<>();
        //拼装路径
        userList.forEach(user->{
            result.add(user.getUsername());
        });
        return CommonResult.success(result);
    }
}
