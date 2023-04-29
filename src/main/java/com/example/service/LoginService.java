package com.example.service;

import com.example.common.CommonResult;

/**
 * @author Seyyu
 * @version 1.0
 */
public interface LoginService {
    CommonResult login(String userid,String password);
}
