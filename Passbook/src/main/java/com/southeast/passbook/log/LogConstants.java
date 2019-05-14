package com.southeast.passbook.log;

/**
 * <h1>日志记录常量定义</h1>
 * Created by 18351 on 2019/5/12.
 */
public class LogConstants {
    /**
     * <h2>用户动作名称</h2>
     */
    public class ActionName{
        public static final String USER_PASS_INFO = "UserPassInfo"; //用户查看优惠券信息

        public static final String USER_USED_PASS_INFO = "UserUsedPassInfo"; //用户查看已使用的优惠券信息

        public static final String USER_USE_PASS = "UserUsePass"; //用户使用优惠券

        public static final String INVENTORY_INFO = "InventoryInfo"; //用户获取库存信息

        public static final String GAIN_PASS_TEMPLATE = "GainPassTemplate"; //用户领取优惠券

        public static final String CREATE_FEEDBACK = "CreateFeedback"; //用户创建评论

        public static final String GET_FEEDBACK = "GetFeedback";//用户获取评论

        public static final String CREATE_USER = "CreateUser"; //创建用户
    }
}
