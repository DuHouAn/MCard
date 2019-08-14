package com.southeast.passbook.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by 18351 on 2019/6/9.
 */
public class UserProxyFactory {
    private Object target;

    public UserProxyFactory(Object target){
        this.target = target;
    }

    public Object getInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object res = null;

                        String methodName = method.getName();
                        if("save".equals(methodName)){
                            System.out.println("添加前增强...");
                            res = method.invoke(target,args);
                            System.out.println("添加后增强...");
                        }else{
                            res = method.invoke(target,args);
                        }
                        return res;
                    }
                }
        );
    }
}
