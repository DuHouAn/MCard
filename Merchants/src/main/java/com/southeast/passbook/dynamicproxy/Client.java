package com.southeast.passbook.dynamicproxy;

/**
 * Created by 18351 on 2019/6/9.
 */
public class Client {
    public static void main(String[] args) {
        IUserDao iuserDao = new UserDao();
        iuserDao.save();
        iuserDao.update();

        UserProxyFactory factory = new UserProxyFactory(iuserDao);
        IUserDao userDao = (IUserDao) factory.getInstance();
        userDao.save();
        userDao.update();
    }
}
