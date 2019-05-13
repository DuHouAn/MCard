package com.southeast.passbook.constants;

/**
 * <h1>优惠券的背景色</h1>
 * Created by 18351 on 2019/5/7.
 */
public enum TemplateColor {
    RED(1,"红色"),
    GREEN(2,"绿色"),
    BLUE(3,"蓝色"),;

    private Integer code; //颜色码
    private String color; //颜色描述

    TemplateColor(Integer code,String color){
        this.code = code;
        this.color = color;
    }

    public Integer getCode() {
        return code;
    }

    public String getColor() {
        return color;
    }
}
