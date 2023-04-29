package com.example.mapper;

import com.example.pojo.Item;
import com.example.pojo.Requirement;
import com.example.pojo.User;
import com.example.pojo.Warehouse;

import java.util.Date;
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 */
public interface RequirementMapper {
    User selectUserByUserId(String userid);
    int setRequirementStateById(int id,String state);
    Warehouse selectWarehouseByName(String warehousename);
    Item selectItemByName(String itemname);
    int addRequirement(String type,
                       int itemid,
                       int warehouseid,
                       int number,
                       int userid,
                       Date datetime,
                       int newwarehouseid);
    List<Requirement> selectRequirement();

}
