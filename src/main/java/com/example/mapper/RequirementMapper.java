package com.example.mapper;

import com.example.pojo.Requirement;
import com.example.pojo.User;

import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 */
public interface RequirementMapper {
    User selectUserByUserId(String userid);
    int setRequirementStateById(int id,String state);
    List<Requirement> selectRequirement();

}
