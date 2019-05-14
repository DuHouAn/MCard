package com.southeast.passbook.constant;

/**
 * <h1>常量s定义</h1>
 * Created by 18351 on 2019/5/12.
 */
public class Constants {

    public static final String TEMPLATE_TOPIC ="merchant-template"; //商户优惠券 Kafka Topic(注意：要与商户模块的一致)

    public static final String TOKEN_DIR = "E:\\Java\\token"; //token 文件存储目录

    public static final String USED_TOKEN_SUFFIX = "_"; //已使用的 token 文件名后缀

    public static final String USE_COUNT_REDIS_KEY = "southeast-user-count"; //总用户数 作为 redis 中的 key

    /**
     * Hbase 表结构设计
     * */
    /** <h2>PassTemplate HBase Table</h2> */
    public class PassTemplateTable {
        public static final String TABLE_NAME = "pa:passtemplate"; //PassTemplate HBase 表名

        //基本信息列族
        public static final String FAMILY_B = "b";

        public static final String ID = "id"; // 商户 id

        public static final String TITLE = "title"; //优惠券标题

        public static final String SUMMARY = "summary"; //优惠券摘要信息

        public static final String DESC = "desc"; //优惠券详细信息

        public static final String HAS_TOKEN = "has_token"; //优惠券是否有 token

        public static final String BACKGROUND = "background";//优惠券背景色

        // 约束信息列族
        public static final String FAMILY_C = "c";

        public static final String LIMIT = "limit"; //最大个数限制

        public static final String START = "start"; //优惠券开始时间

        public static final String END = "end"; //优惠券结束时间
    }

    /** <h2> Pass HBase Table </h2>*/
    public class PassTable {

        public static final String TABLE_NAME = "pa:pass"; //Pass HBase 表名

        //信息列族
        public static final String FAMILY_I = "i";

        public static final String USER_ID = "user_id"; //用户 id

        public static final String TEMPLATE_ID = "template_id"; //优惠券 id

        public static final String TOKEN = "token"; //优惠券识别码

        public static final String ASSIGNED_DATE = "assigned_date"; //领取日期

        public static final String CON_DATE = "con_date"; //消费日期
    }

    /** <h2> Feedback Hbase Table </h2>*/
    public class Feedback {

        public static final String TABLE_NAME = "pa:feedback"; //Feedback HBase 表名

        //信息列族
        public static final String FAMILY_I = "i";

        public static final String USER_ID = "user_id"; //用户 id

        public static final String TYPE = "type"; //评论类型

        public static final String TEMPLATE_ID = "template_id"; //PassTemplate RowKey, 如果是 app 评论, 则是 -1

        public static final String COMMENT = "comment"; //评论内容
    }

    /** <h2>User HBase Table</h2> */
    public class UserTable {

        public static final String TABLE_NAME = "pa:user"; //User HBase 表名

        // 基本信息列族
        public static final String FAMILY_B = "b";

        public static final String NAME = "name"; //用户名

        public static final String AGE = "age"; //用户年龄

        public static final String SEX = "sex"; //用户性别

        // 额外信息列族
        public static final String FAMILY_O = "o";

        public static final String PHONE = "phone"; //电话号码

        public static final String ADDRESS = "address"; //住址
    }
}
