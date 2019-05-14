package com.southeast.passbook.constant;

/**
 * <h1>评论类型枚举</h1>
 * Created by 18351 on 2019/5/12.
 */
public enum FeedbackType {

    PASS("pass", "针对优惠券的评论"),
    APP("app", "针对卡包 App 的评论");

    /** 评论类型编码 */
    private String code;

    /** 评论类型描述 */
    private String desc;

    FeedbackType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
