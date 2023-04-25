package com.example.mapper;

import com.example.pojo.User;

import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
public interface InfMapper {
    List<User> selectUser();
    User selectUserByUserId(String userid);
}
