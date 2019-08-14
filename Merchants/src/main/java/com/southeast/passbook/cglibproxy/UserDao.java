package com.southeast.passbook.cglibproxy;

/**
 * Created by 18351 on 2019/6/9.
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("添加功能...");
    }

    @Override
    public void update() {
        System.out.println("更新功能...");
    }
}
