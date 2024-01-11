
/**
 * @Author java实战基地
 * @Version 3501754007
 */
package com.itmk.utils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultVo<T> {
    //返回前端的信息提示
    private String msg;
    //返回前端的状态码
    private int code;
    //返回的数据
    private T data;
}