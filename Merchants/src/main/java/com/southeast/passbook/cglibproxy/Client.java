package com.southeast.passbook.cglibproxy;

import com.southeast.passbook.dynamicproxy.IUserDao;
import com.southeast.passbook.dynamicproxy.UserDao;

/**
 * Created by 18351 on 2019/6/9.
 */
public class Client {
    public static void main(String[] args) {
        IUserDao iuserDao = new UserDao();
        iuserDao.save();
        iuserDao.update();

        System.out.println("================");

        UserDaoProxyFactory2 proxyFactory2 = new UserDaoProxyFactory2(iuserDao);
        IUserDao userDao = (IUserDao)proxyFactory2.getInstance();
        userDao.save();
        userDao.update();
    }
}
