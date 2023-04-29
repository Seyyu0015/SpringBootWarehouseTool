package com.example.mapper;

import com.example.pojo.Item;
import com.example.pojo.Storage;
import com.example.pojo.User;
import com.example.pojo.Warehouse;

import java.util.Date;
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
public interface InfMapper {
    //User
    User selectUserByUserName(String name);
    int addUser(String userid,String password,String username,String permission);
    int setUser(String userid,String password,String username,String permission);
    int delUser(String userid);
    List<User> selectUser();
    User selectUserByUserId(String userid);

    //Storage
    Storage selectStorageByIW(int itemid, int warehouseid);
    List<Storage> selectStorage();
    int addStorage(int itemid, int warehouseid,int number);
    int setStorage(int itemid, int warehouseid,int number);

    //Item
    int addItem(String itemname, String unit, Date date);
    int setItem(String itemname, String newname, String unit);
    Item selectItemById(int itemid);
    Item selectItemByName(String itemname);

    //Warehouse
    List<Warehouse> selectWarehouse();
    int addWarehouse(String name, String location);
    int setWarehouse(String newname, String name, String location);
    Warehouse selectWarehouseByName(String warehousename);
    Warehouse selectWarehouseById(int warehouseid);
}
