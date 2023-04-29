package com.example.service;

import com.example.common.CommonResult;

/**
 * @author Seyyu
 * @version 1.0
 */
public interface InfService {
    CommonResult queryUserList(String userid,String password,String byid,String byper);

    CommonResult selectStorageList(String itemname, String warehousename);

    CommonResult selectWarehouse();

    CommonResult addStorage(int itemid, int warehouseid, int number);

    CommonResult setStorage(int itemid, int warehouseid, int number);

    CommonResult addItem(String itemname, String unit);

    CommonResult setItem(String itemname, String newname, String unit);

    CommonResult addWarehouse(String name,String location);

    CommonResult setWarehouse(String newname, String location, String name);


    CommonResult addUser(String userid, String username, String password, String permission);

    CommonResult setUser(String userid, String username, String password, String permission);

    CommonResult delUser(String userid, String password);
}
