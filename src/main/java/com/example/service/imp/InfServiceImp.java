package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.InfMapper;
import com.example.mapper.LoginMapper;
import com.example.pojo.*;
import com.example.service.InfService;
import com.example.service.LoginService;
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
public class InfServiceImp implements InfService {

    @Resource
    private InfMapper infMapper;

    @Override
    public CommonResult queryUserList(String byid,String byper) {

        List<User> users = infMapper.selectUser();
        //应用筛选
        List<User> result = new ArrayList<>();
        if(byid != null && byper != null){
            for (User u : users){
                if (u.getUserid().equals(byid) && u.getPermission().equals(byper)){
                    result.add(u);
                }
            }
        }else if(byid != null){
            for (User u : users){
                if ((u.getUserid().equals(byid))){
                    result.add(u);
                }
            }
        }else if(byper != null){
            for (User u : users){
                if (u.getPermission().equals(byper)){
                    result.add(u);
                }
            }
        }else {
            result = users;
        }
        return CommonResult.success(result);

    }

    @Override
    public CommonResult selectStorageList(String itemname, String warehousename) {
        int i = 0;
        Item item = infMapper.selectItemByName(itemname);
        Warehouse warehouse = infMapper.selectWarehouseByName(warehousename);

        List<Storage> allstorages = infMapper.selectStorage();
        List<StorageDisplay> storageDisplays = new ArrayList<>();

        for (Storage s : allstorages) {
            Item theitem = infMapper.selectItemById(s.getItemid());
            Warehouse thewarehouse = infMapper.selectWarehouseById(s.getWarehouseid());

            //转化为display形势的个体记录
            i++;
            StorageDisplay newsd = new StorageDisplay(
                    i,
                    s.getItemid(),
                    theitem.getItemname(),
                    s.getItemnumber(),
                    theitem.getUnit(),
                    s.getWarehouseid(),
                    thewarehouse.getName(),
                    s.getItemnumber(),
                    theitem.getDatetime()
            );
            storageDisplays.add(newsd);
        }

        //计算总量
        for (StorageDisplay sd : storageDisplays){
            for (StorageDisplay sd2 : storageDisplays){
                if(sd.getSdid() > sd2.getSdid() && sd.getId() == sd2.getId()){
                    int total = sd.getTotalnumber() + sd2.getTotalnumber();
                    sd.setTotalnumber(total);
                    sd2.setTotalnumber(total);
                }
            }
        }

        //应用筛选
        List<StorageDisplay> result = new ArrayList<>();
        for (StorageDisplay sd : storageDisplays){
            if ((itemname.isEmpty() || sd.getItemname().equals(itemname))&&(warehousename.isEmpty() || sd.getWarehousename().equals(warehousename))){
                result.add(sd);
            }
        }
        return CommonResult.success(result);
    }

    @Override
    public CommonResult selectWarehouse() {
        return CommonResult.success(infMapper.selectWarehouse());
    }

    @Override
    public CommonResult addStorage(int itemid, int warehouseid, int number) {
        //初始化
        Storage exsd = infMapper.selectStorageByIW(itemid,warehouseid);
        Warehouse warehouse = infMapper.selectWarehouseById(warehouseid);
        Item item = infMapper.selectItemById(itemid);

        //验证合法性
        if(warehouse == null || item ==null){
            return CommonResult.success("未找到该物品或仓库 请检查id输入是否正确");
        }

        //执行操作
        if (exsd != null){
            //记录已存在
            int tonumber = number + exsd.getItemnumber();
            try{
                infMapper.setStorage(itemid,warehouseid,tonumber);
                return CommonResult.success("向仓库 "+warehouse.getName()+" 添加 "+item.getItemname()+"*"+number+"成功");
            }catch (Exception e){
                return CommonResult.fail(500, String.valueOf(e));
            }

        }else {
            //记录不存在
            try{
                infMapper.addStorage(itemid,warehouseid,number);
                return CommonResult.success("向仓库 "+warehouse.getName()+" 添加 "+item.getItemname()+"*"+number+"成功");
            }catch (Exception e){
                return CommonResult.fail(500, String.valueOf(e));
            }
        }
    }

    @Override
    public CommonResult setStorage(int itemid, int warehouseid, int number) {
        //初始化
        Storage exsd = infMapper.selectStorageByIW(itemid,warehouseid);

        //验证合法性
        if(exsd == null){
            return CommonResult.success("未找到该物品或仓库 请检查id输入是否正确");
        }

        //执行操作
        try{
            infMapper.setStorage(itemid,warehouseid,number);
            return CommonResult.success("修改成功");
        }catch (Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    @Override
    public CommonResult addItem(String itemname, String unit) {
        Item exitem = infMapper.selectItemByName(itemname);
        if(exitem != null){
            return CommonResult.success("请求被拒绝 同名物品已存在");
        }
        try{
            Date time= new java.sql.Date(new java.util.Date().getTime());
            infMapper.addItem(itemname,unit,time);
            return CommonResult.success("新增物品成功");
        }catch (Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    @Override
    public CommonResult setItem(String itemname, String newname, String unit) {
        Item exitem = infMapper.selectItemByName(newname);
        Item exitem2 = infMapper.selectItemByName(itemname);
        if(exitem != null || exitem2 == null){
            return CommonResult.success("请求被拒绝 未找到该物品或同名物品已存在");
        }
        try{
            Date time= new java.sql.Date(new java.util.Date().getTime());
            infMapper.setItemDateById(exitem2.getId(),time);
            infMapper.setItem(itemname,newname,unit);

            return CommonResult.success("修改成功");
        }catch (Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    @Override
    public CommonResult addWarehouse(String name, String location) {
        Warehouse exwarehouse = infMapper.selectWarehouseByName(name);
        if(exwarehouse != null){
            return CommonResult.success("请求被拒绝 同名仓库已存在");
        }
        try{
            infMapper.addWarehouse(name,location);
            return CommonResult.success("新增成功");
        }catch (Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    @Override
    public CommonResult setWarehouse(String newname, String location, String name) {
        Warehouse exwarehouse = infMapper.selectWarehouseByName(name);
        Warehouse exwarehouse2 = infMapper.selectWarehouseByName(newname);
        if(exwarehouse2 != null || exwarehouse == null){
            return CommonResult.success("请求被拒绝 未找到该仓库或同名仓库已存在");
        }
        try{
            infMapper.setWarehouse(newname,name,location);
            return CommonResult.success("修改成功");
        }catch (Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    @Override
    public CommonResult addUser(String userid, String username, String password, String permission) {
        User exuser = infMapper.selectUserByUserId(userid);
        if(exuser != null){
            return CommonResult.success("请求被拒绝 该id已存在");
        }
        User exuser2 = infMapper.selectUserByUserName(username);
        if(exuser2 != null){
            return CommonResult.success("请求被拒绝 该名称已存在");
        }
        try{
            infMapper.addUser(userid,password,username,permission);
            return CommonResult.success("新增成功");
        }catch (Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    @Override
    public CommonResult setUser(String userid, String username, String password, String permission) {
        User exuser = infMapper.selectUserByUserId(userid);
        if(exuser == null){
            return CommonResult.success("请求被拒绝 找不到指定id");
        }
        User exuser2 = infMapper.selectUserByUserName(username);
        if(exuser2 != null){
            return CommonResult.success("请求被拒绝 该名称已存在");
        }
        try{
            infMapper.setUser(userid,password,username,permission);
            return CommonResult.success("修改成功");
        }catch (Exception e){
            return CommonResult.fail(500, String.valueOf(e));
        }
    }

    @Override
    public CommonResult delUser(String userid, String password) {
        User exuser = infMapper.selectUserByUserId(userid);
        if(exuser == null){
            return CommonResult.success("请求被拒绝 找不到指定id");
        }
        if(exuser.getPassword().equals(password)){
            infMapper.delUser(userid);
            return CommonResult.success("删除成功");
        }else {
            return CommonResult.success("请求被拒绝 密码错误");
        }
    }

}