package com.example.service.imp;

import com.example.common.CommonResult;
import com.example.mapper.FoodMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Food;
import com.example.pojo.User;
import com.example.service.CalculatorService;
import com.example.service.FoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@Service
public class CalculatorServiceImp implements CalculatorService {

    @Resource
    private FoodMapper foodMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public CommonResult calculator() {
        List<User> userList = userMapper.selectUser();
        List<Food> foodList = foodMapper.selectFood();

        if (userList == null || userList.isEmpty() || foodList == null || foodList.isEmpty()) {
            return CommonResult.fail(500, "查询数据信息为空");
        }

        //结果集
        HashMap<Integer, Double> result = new HashMap<>();
        for (User user : userList) {
            result.put(user.getId(), (double) 0);
        }

        //遍历每个食材
        for (Food food : foodList) {
            //人数（避免÷0）
            int numberofperson = food.getUser1ate() +
                    food.getUser2ate() +
                    food.getUser3ate() +
                    food.getUser4ate() +
                    food.getUser5ate();
            //System.out.println(food.getFood() + "的食用人数是:" + numberofperson);

            //每人应该支付的金额
            double perpersonpaid = 0;
            if (numberofperson != 0) {
                perpersonpaid = food.getValue() / numberofperson;
            }
            //System.out.println(food.getFood() + "的每人金额是:" + perpersonpaid);

            //按人结算
            if (food.getUser1ate() == 1 && userList.size()>=1) {
                result.put(1, result.get(1) + perpersonpaid);
            }
            if (food.getUser2ate() == 1 && userList.size()>=2) {
                result.put(2, result.get(2) + perpersonpaid);
            }
            if (food.getUser3ate() == 1 && userList.size()>=3) {
                result.put(3, result.get(3) + perpersonpaid);
            }
            if (food.getUser4ate() == 1 && userList.size()>=4) {
                result.put(4, result.get(4) + perpersonpaid);
            }
            if (food.getUser5ate() == 1 && userList.size()>=5) {
                result.put(5, result.get(5) + perpersonpaid);
            }
        }

        //包装结果
        HashMap<String, Double> packedresult = new HashMap<>();
        int i =1;
        for (User user : userList) {
            packedresult.put(user.getUsername(),result.get(i));
            i++;
        }


        return CommonResult.success(packedresult);
    }
}
