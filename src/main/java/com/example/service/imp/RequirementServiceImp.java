package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.InfMapper;
import com.example.mapper.LoginMapper;
import com.example.mapper.RequirementMapper;
import com.example.pojo.*;
import com.example.service.InfService;
import com.example.service.LoginService;
import com.example.service.RequirementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Seyyu
 * @version 1.0
 */
@Service
public class RequirementServiceImp implements RequirementService {

    @Resource
    private RequirementMapper requirementMapper;

    @Resource
    private InfMapper infMapper;

    @Override
    public CommonResult selectRequirement(String userid) {
        //初始化参数
        List<Requirement> requirementList = requirementMapper.selectRequirement();
        List<Requirement> result = new ArrayList<>();

        //应用筛选
        if (!requirementList.isEmpty() && !userid.equals("")){
            for(Requirement r : requirementList){
                User user = requirementMapper.selectUserByUserId(userid);
                if(user == null){
                    List<Requirement> nullresult = new ArrayList<>();
                    return CommonResult.success(nullresult);
                }
                if(r.getUserid().equals(user.getUserid())){
                    result.add(r);
                }
            }
        }else {
            result = requirementList;
        }

        //后期处理
        List<RequirementDisplay> new_result = new ArrayList<>();
        for(Requirement r : result){
            if(r.getNewwarehouseid()==0){
                RequirementDisplay rd = new RequirementDisplay(
                        r.getId(),
                        r.getType(),
                        infMapper.selectItemById(r.getItemid()).getItemname(),
                        infMapper.selectWarehouseById(r.getWarehouseid()).getName(),
                        r.getNumber(),
                        infMapper.selectUserByUserId(r.getUserid()).getUsername(),
                        r.getDatetime(),
                        r.getState(),
                        ""
                );
                new_result.add(rd);
            }else {
                RequirementDisplay rd = new RequirementDisplay(
                        r.getId(),
                        r.getType(),
                        infMapper.selectItemById(r.getItemid()).getItemname(),
                        infMapper.selectWarehouseById(r.getWarehouseid()).getName(),
                        r.getNumber(),
                        infMapper.selectUserByUserId(r.getUserid()).getUsername(),
                        r.getDatetime(),
                        r.getState(),
                        infMapper.selectWarehouseById(r.getNewwarehouseid()).getName()
                );
                new_result.add(rd);
            }

        }
        Collections.reverse(new_result);
        return CommonResult.success(new_result);
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
                return CommonResult.success("新仓库不存在");
            }else {

                requirementMapper.addRequirementTransfer(
                        type,
                        exi.getId(),
                        exw.getId(),
                        number,
                        user.getUserid(),
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
                user.getUserid(),
                time);
        return CommonResult.success("请求创建成功");
    }

    @Override
    public CommonResult setRequirement(int id,String state) {
        Requirement re = requirementMapper.selectRequirementById(id);
        System.out.println(re.getState());
        //未处理
        if (re.getState().equals("Untreated") || re.getState().equals("Rejected")){
            //->已完成
            if(state.equals("Implemented")){
                switch (re.getType()) {
                    //入库
                    case "Add":
                        if (addStorage(re.getItemid(), re.getWarehouseid(), re.getNumber())) {
                            requirementMapper.setRequirementStateById(id, state);
                            return CommonResult.success("执行完毕 " + re.getState() + "->" + state);
                        } else {
                            return CommonResult.success("执行失败 无法执行申请");
                        }
                    //出库
                    case "Out":
                        if (removeStorage(re.getItemid(), re.getWarehouseid(), re.getNumber())) {
                            requirementMapper.setRequirementStateById(id, state);
                            return CommonResult.success("执行完毕 " + re.getState() + "->" + state);
                        } else {
                            return CommonResult.success("执行失败 无法执行申请");
                        }
                    //转移
                    case "Transfer":
                        if (removeStorage(re.getItemid(), re.getWarehouseid(), re.getNumber())
                                && addStorage(re.getItemid(), re.getNewwarehouseid(), re.getNumber())) {
                            requirementMapper.setRequirementStateById(id, state);
                            return CommonResult.success("执行完毕 " + re.getState() + "->" + state);
                        } else {
                            return CommonResult.success("执行失败 无法执行申请");
                        }
                }

            //->拒绝
            }else if(state.equals("Rejected")){
                requirementMapper.setRequirementStateById(id,state);
                return CommonResult.success("执行完毕 "+re.getState()+"->"+state);
            }

        //已完成->撤销
        }else if(re.getState().equals("Implemented") && state.equals("Canceled")){
            switch (re.getType()) {
                //出库
                case "Out":
                    if (addStorage(re.getItemid(), re.getWarehouseid(), re.getNumber())) {
                        requirementMapper.setRequirementStateById(id, state);
                        return CommonResult.success("执行完毕 " + re.getState() + "->" + state);
                    } else {
                        return CommonResult.success("执行失败 无法执行申请");
                    }
                    //入库
                case "Add":
                    if (removeStorage(re.getItemid(), re.getWarehouseid(), re.getNumber())) {
                        requirementMapper.setRequirementStateById(id, state);
                        return CommonResult.success("执行完毕 " + re.getState() + "->" + state);
                    } else {
                        return CommonResult.success("执行失败 无法执行申请");
                    }
                    //转移
                case "Transfer":
                    if (removeStorage(re.getItemid(), re.getNewwarehouseid(), re.getNumber())
                            && addStorage(re.getItemid(), re.getWarehouseid(), re.getNumber())) {
                        requirementMapper.setRequirementStateById(id, state);
                        return CommonResult.success("执行完毕 " + re.getState() + "->" + state);
                    } else {
                        return CommonResult.success("执行失败 无法执行申请");
                    }
            }
        }
        return CommonResult.success("非法转化方式"+re.getState()+"->"+state);
    }

    public boolean addStorage(int itemid, int warehouseid, int number) {
        //初始化
        Storage exsd = infMapper.selectStorageByIW(itemid,warehouseid);
        Warehouse warehouse = infMapper.selectWarehouseById(warehouseid);
        Item item = infMapper.selectItemById(itemid);

        //验证合法性
        if(warehouse == null || item ==null){
            return false;
        }

        //执行操作
        if (exsd != null){
            //记录已存在
            int tonumber = number + exsd.getItemnumber();
            infMapper.setStorage(itemid,warehouseid,tonumber);
            Date time= new java.sql.Date(new java.util.Date().getTime());
            infMapper.setItemDateById(itemid,time);
        }else {
            //记录不存在
            infMapper.addStorage(itemid,warehouseid,number);
        }
        return true;
    }

    public boolean removeStorage(int itemid, int warehouseid, int number) {
        //初始化
        Storage exsd = infMapper.selectStorageByIW(itemid,warehouseid);
        Warehouse warehouse = infMapper.selectWarehouseById(warehouseid);
        Item item = infMapper.selectItemById(itemid);

        //验证合法性
        if(warehouse == null || item ==null){
            return false;
        }

        //执行操作
        if (exsd != null){
            //记录已存在
            if(exsd.getItemnumber()<number){
                return false;
            }

            int tonumber = exsd.getItemnumber() - number;
            infMapper.setStorage(itemid,warehouseid,tonumber);
            Date time= new java.sql.Date(new java.util.Date().getTime());
            infMapper.setItemDateById(itemid,time);
            return true;

        }else {
            //记录不存在
            return false;
        }
    }


}