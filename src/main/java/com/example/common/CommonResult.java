package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022/8/28 11:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    /**
     * 响应码状态
     */
    private int status;

    /**
     * 返回结果集
     */
    private T data;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 是否有更多信息
     */
    private Boolean hasMore;

    /**
     * 返回成功方法
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult success(T data){
        return new CommonResult(200,data,"请求成功",null);
    }

    public static <T> CommonResult success(String msg){
        return new CommonResult(200,null,msg,null);
    }

    public static <T> CommonResult success(T data,Boolean hasMore){
        return new CommonResult(200,data,"请求成功",hasMore);
    }
    /**
     * 返回错误方法
     * @param msg
     * @return
     */
    public static CommonResult fail(int code,String msg){
        return new CommonResult(code,null,msg,null);
    }
}
