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
        HashMap<Integer, Float> result = new HashMap<Integer, Float>();
        for (User user : userList) {
            result.put(user.getId(), (float) 0);
        }

        for (Food food : foodList) {
            //人数（避免÷0）
            int numberofperson = food.getUser1ate() +
                    food.getUser2ate() +
                    food.getUser3ate() +
                    food.getUser4ate() +
                    food.getUser5ate();

            //每人应该支付的金额
            float perpersonpaid = 0;
            if ((food.getUser1ate() == 1) && (numberofperson != 0)) {
                perpersonpaid = food.getValue() / numberofperson;
            }

            //按人结算
            if (food.getUser1ate() == 1) {
                result.put(1, result.get(1) + perpersonpaid);
            } else if (food.getUser2ate() == 1) {
                result.put(2, result.get(2) + perpersonpaid);
            } else if (food.getUser3ate() == 1) {
                result.put(3, result.get(3) + perpersonpaid);
            } else if (food.getUser4ate() == 1) {
                result.put(4, result.get(4) + perpersonpaid);
            } else if (food.getUser5ate() == 1) {
                result.put(5, result.get(5) + perpersonpaid);
            }
        }


        return CommonResult.success(result);
    }
}
