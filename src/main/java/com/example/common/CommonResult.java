package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    //响应码状态
    private int status;
    //返回结果集
    private T data;
    //响应信息
    private String msg;

    //返回成功方法
    public static <T> CommonResult success(T data){return new CommonResult(200,data,"请求成功");}
    //返回错误方法
    public static CommonResult fail(int code,String msg){
        return new CommonResult(code,null,msg);
    }
}
