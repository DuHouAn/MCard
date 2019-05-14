package com.southeast.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>Controller 统一的响应</h1>
 * Created by 18351 on 2019/5/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Integer errorCode = 0; //错误码 ，正确则返回 0

    private String errorMsg = ""; //错误信息，正确则返回空字符串

    private Object data; //返回值对象

    /**
     * 正确响应的构造函数
     */
    public Response(Object data) {
        this.data = data;
    }

    /**
     * 空响应
     * */
    public static Response success() {
        return new Response();
    }

    /**
     * 错误响应
     * */
    public static Response failure(String errorMsg) {
        return new Response(-1, errorMsg, null);
    }
}
