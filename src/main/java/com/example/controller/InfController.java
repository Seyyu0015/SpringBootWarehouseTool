package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.InfService;
import com.example.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seyyu
 * @version 1.0
 */

@Tag(name = "查询管理", description = "InfController")
@Controller
@CrossOrigin
public class InfController {

    @Autowired
    private InfService infService;

    //User
    @Operation(summary = "查询用户列表")
    @GetMapping("/user")
    @ResponseBody
    public CommonResult selectUserList(String byid,String byper){
        if(byid!=null&&byid.equals("")){byid = null;}
        if(byper!=null&&byper.equals("")){byper = null;}
        return infService.queryUserList(byid,byper);
    }

    @Operation(summary = "添加用户")
    @GetMapping("/addUser")
    @ResponseBody
    public CommonResult addUser(String userid,String username,String password,String permission){
        return infService.addUser(userid,username,password,permission);
    }

    @Operation(summary = "修改用户")
    @GetMapping("/setUser")
    @ResponseBody
    public CommonResult setUser(String userid,String username,String password,String permission){
        return infService.setUser(userid,username,password,permission);
    }

    @Operation(summary = "删除用户")
    @GetMapping("/delUser")
    @ResponseBody
    public CommonResult delUser(String userid,String password){
        return infService.delUser(userid,password);
    }

    //storage
    @Operation(summary = "查询库存列表")
    @GetMapping("/storage")
    @ResponseBody
    public CommonResult selectStorageList(String itemname, String warehousename){
        if(itemname!=null&&itemname.equals("")){itemname = null;}
        if(warehousename!=null&&warehousename.equals("")){warehousename = null;}
        return infService.selectStorageList(itemname,warehousename);
    }

    @Operation(summary = "添加记录")
    @GetMapping("/addStorage")
    @ResponseBody
    public CommonResult addStorage(int itemid, int warehouseid,int number){
        return infService.addStorage(itemid,warehouseid,number);
    }

    @Operation(summary = "修改记录")
    @GetMapping("/setStorage")
    @ResponseBody
    public CommonResult setStorage(int itemid, int warehouseid,int number){
        return infService.setStorage(itemid,warehouseid,number);
    }

    //warehouse
    @Operation(summary = "查询仓库列表")
    @GetMapping("/warehouse")
    @ResponseBody
    public CommonResult selectWarehouse(){
        return infService.selectWarehouse();
    }

    @Operation(summary = "添加仓库")
    @GetMapping("/addWarehouse")
    @ResponseBody
    public CommonResult addWarehouse(String name,String location){
        return infService.addWarehouse(name,location);
    }

    @Operation(summary = "修改仓库")
    @GetMapping("/setWarehouse")
    @ResponseBody
    public CommonResult setWarehouse(String newname, String location, String name){
        return infService.setWarehouse(newname,location,name);
    }

    //item
    @Operation(summary = "添加物品")
    @GetMapping("/item")
    @ResponseBody
    public CommonResult item(){
        return infService.selectItem();
    }

    @Operation(summary = "添加物品")
    @GetMapping("/addItem")
    @ResponseBody
    public CommonResult addItem(String itemname,String unit){
        return infService.addItem(itemname,unit);
    }

    @Operation(summary = "修改物品")
    @GetMapping("/setItem")
    @ResponseBody
    public CommonResult setItem(String itemname, String newname, String unit){
        return infService.setItem(itemname,newname,unit);
    }



}
