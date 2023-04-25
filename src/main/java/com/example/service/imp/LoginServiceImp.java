package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.LoginMapper;
import com.example.pojo.User;
import com.example.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@Service
public class LoginServiceImp implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public CommonResult login(String userid, String password) {
        try {
            User user = loginMapper.findUserByUserId(userid);

            //非法验证 账号不存在
            if (user == null) {
                return CommonResult.success("账号不存在");
            }

            if (password.equals(user.getPassword())) {
                return CommonResult.success(user);
            } else {
                return CommonResult.success("账号密码不匹配");
            }
        }catch(Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

}