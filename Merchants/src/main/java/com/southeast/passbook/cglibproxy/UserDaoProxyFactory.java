package com.southeast.passbook.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by 18351 on 2019/6/9.
 */
public class UserDaoProxyFactory implements MethodInterceptor{
    private Object target;

    public UserDaoProxyFactory(Object target) {
        this.target = target;
    }

    public Object getInstance(){
        //1.工具类
        Enhancer enchancer=new Enhancer();
        //2.设置父类
        enchancer.setSuperclass(target.getClass());
        //3.设置回调函数
        enchancer.setCallback(this);
        //4.创建子类（代理对象）
        return enchancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
        Object res = null;
        String methodName = method.getName();

        if("save".equals(methodName)){
            System.out.println("添加前增强...");
            res = method.invoke(target,objects);
            System.out.println("添加后增强...");
        }else{
            res = method.invoke(target,objects);
        }

        return res;
    }
}
