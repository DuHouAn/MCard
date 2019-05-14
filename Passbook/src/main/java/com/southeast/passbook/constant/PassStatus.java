package com.southeast.passbook.constant;

/**
 * <h1>优惠券的状态</h1>
 * Created by 18351 on 2019/5/12.
 */
public enum PassStatus {

    UNUSED(1,"未被使用的"),
    USED(2,"已经使用的"),
    ALL(3,"全部领取的");

    private Integer code;//状态码
    private String desc;//状态描述
    PassStatus(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
