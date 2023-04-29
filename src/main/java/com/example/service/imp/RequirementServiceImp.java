package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.LoginMapper;
import com.example.mapper.RequirementMapper;
import com.example.pojo.Item;
import com.example.pojo.Requirement;
import com.example.pojo.User;
import com.example.pojo.Warehouse;
import com.example.service.LoginService;
import com.example.service.RequirementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
        //初始化参数
        Warehouse exw = requirementMapper.selectWarehouseByName(warehouse);
        Warehouse exw2 = requirementMapper.selectWarehouseByName(newwarehouse);
        Date time= new java.sql.Date(new java.util.Date().getTime());
        Item exi = requirementMapper.selectItemByName(itemname);
        User user = requirementMapper.selectUserByUserId(userid);

        //非法验证
        if(exw == null){
            return CommonResult.success("仓库不存在");
        }
        if(exi == null){
            return CommonResult.success("物品不存在");
        }
        if(type.equals("Transfer")){
            if(exw2 == null) {
                return CommonResult.success("新仓库");
            }else {
                requirementMapper.addRequirement(type,
                        exi.getId(),
                        exw.getId(),
                        number,
                        user.getId(),
                        time,
                        exw2.getId()
                );
                return CommonResult.success("请求创建成功");
            }
        }

        requirementMapper.addRequirement(type,
                exi.getId(),
                exw.getId(),
                number,
                user.getId(),
                time,
                null
                );
        return CommonResult.success("请求创建成功");
    }

    @Override
    public CommonResult setRequirement(int id,String state) {
        return null;
    }
}