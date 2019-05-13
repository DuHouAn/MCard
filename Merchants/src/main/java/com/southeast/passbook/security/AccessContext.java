package com.southeast.passbook.security;

/**
 * <h1>访问上下文</h1>
 * 使用 ThreadLocal 去单独存储每一个线程携带的 Token 信息
 * Created by 18351 on 2019/5/7.
 */
public class AccessContext {
    private static final  ThreadLocal<String> token =
            new ThreadLocal<String>(); // token 是 String 类型

    public static String getToken(){
        return token.get();
    }

    public static void setToken(String tokenStr){
        token.set(tokenStr);
    }

    /**
     * 删除当前线程的 token 信息
     */
    public static void clearAccessKey(){
        token.remove();
    }
}
