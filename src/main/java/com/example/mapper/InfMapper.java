package com.example.mapper;

import com.example.pojo.Item;
import com.example.pojo.Storage;
import com.example.pojo.User;
import com.example.pojo.Warehouse;

import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
public interface InfMapper {
    List<User> selectUser();
    User selectUserByUserId(String userid);
    Storage selectStorageByIW(int itemid, int warehouseid);
    List<Storage> selectStorage();
    List<Warehouse> selectWarehouse();
    int addStorage(int itemid, int warehouseid,int number);
    int setStorage(int itemid, int warehouseid,int number);
    Item selectItemById(int itemid);
    Item selectItemByName(String itemname);
    Warehouse selectWarehouseByName(String warehousename);
    Warehouse selectWarehouseById(int warehouseid);
}
