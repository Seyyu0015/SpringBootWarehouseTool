package com.example.service;

import com.example.common.CommonResult;

/**
 * @author Seyyu
 * @version 1.0
 */
public interface RequirementService {
    CommonResult selectRequirement(String userid);

    CommonResult addRequirement(String type,
                                String itemname,
                                String warehouse,
                                int number,
                                String userid,
                                String newwarehouse);

    CommonResult setRequirement(int id,String state);
}
