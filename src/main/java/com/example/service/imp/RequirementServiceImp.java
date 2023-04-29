package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.LoginMapper;
import com.example.mapper.RequirementMapper;
import com.example.pojo.Requirement;
import com.example.pojo.User;
import com.example.service.LoginService;
import com.example.service.RequirementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 */
@Service
public class RequirementServiceImp implements RequirementService {

    @Resource
    private RequirementMapper requirementMapper;

    @Override
    public CommonResult selectRequirement(String userid) {
        //初始化参数
        List<Requirement> requirementList = requirementMapper.selectRequirement();
        List<Requirement> result = new ArrayList<>();

        //应用筛选
        if (!requirementList.isEmpty() && !userid.isEmpty()){
            for(Requirement r : requirementList){
                User user = requirementMapper.selectUserByUserId(userid);
                if(r.getUserid() == user.getId()){
                    result.add(r);
                }
            }
        }else {
            result = requirementList;
        }
        return CommonResult.success(result);
    }

    @Override
    public CommonResult addRequirement(String type,String itemname,String warehouse,int number,String userid,String newwarehouse) {
        User user = requirementMapper.selectUserByUserId(userid);
        int uid = user.getId();

        return null;
    }

    @Override
    public CommonResult setRequirement(int id,String state) {
        return null;
    }
}