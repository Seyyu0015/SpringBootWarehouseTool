package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.InfMapper;
import com.example.mapper.LoginMapper;
import com.example.pojo.User;
import com.example.service.InfService;
import com.example.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@Service
public class InfServiceImp implements InfService {

    @Resource
    private InfMapper infMapper;

    @Override
    public CommonResult queryUserList(String userid, String password) {
        try {
            //权限确认
            User user = infMapper.selectUserByUserId(userid);
            if (user == null) {
                return CommonResult.success("身份验证失败");
            }

            if (password.equals(user.getPassword())) {
                return CommonResult.success(infMapper.selectUser());
            } else {
                return CommonResult.success("身份验证失败");
            }
        }catch(Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

}