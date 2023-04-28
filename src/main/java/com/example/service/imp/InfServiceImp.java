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
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@Service
public class InfServiceImp implements InfService {

    @Resource
    private InfMapper infMapper;

    @Override
    public CommonResult queryUserList(String userid, String password) {
        try {
            //权限确认
            User user = infMapper.selectUserByUserId(userid);
            if (user == null) {
                return CommonResult.success("身份验证失败");
            }

            if (password.equals(user.getPassword())) {
                return CommonResult.success(infMapper.selectUser());
            } else {
                return CommonResult.success("身份验证失败");
            }
        } catch (Exception e) {
            return CommonResult.fail(500, String.valueOf(e));
        }
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
        System.out.print(itemname);
        return CommonResult.success(result);
    }

    @Override
    public CommonResult selectWarehouse() {
        return CommonResult.success(infMapper.selectWarehouse());
    }

}