package com.southeast.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>通用的响应对象</h1>
 * Created by 18351 on 2019/5/7.
 */
@Data  //使用该注解，可以实现 getter、setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Integer errorCode = 0; //错误码，正确返回 0

    private String errorMsg = ""; //错误信息，正确返回空字符串

    private Object data; // 返回值对象

    /**
     * 正确的响应构造函数
     * @param data 返回值对象
     */
    public Response(Object data){
        this.data = data;
    }
}
