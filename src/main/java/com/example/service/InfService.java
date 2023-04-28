package com.example.service;

import com.example.common.CommonResult;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
public interface InfService {
    CommonResult queryUserList(String userid,String password);

    CommonResult selectStorageList(String itemname, String warehousename);

    CommonResult selectWarehouse();

    CommonResult addStorage(int itemid, int warehouseid, int number);

    CommonResult setStorage(int itemid, int warehouseid, int number);

    CommonResult addItem(String itemname, String unit);

    CommonResult setItem(String itemname, String newname, String name);
}
