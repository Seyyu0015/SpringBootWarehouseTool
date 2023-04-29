package com.example.mapper;

import com.example.pojo.User;

import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 */
public interface LoginMapper {
    User findUserByUserId(String userid);
}
