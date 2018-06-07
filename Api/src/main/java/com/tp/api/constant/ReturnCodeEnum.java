package com.tp.api.constant;


public enum ReturnCodeEnum {



    CODE_200(Integer.valueOf(200), "message.common.success"),
    CODE_404(Integer.valueOf(404), "message.common.notfound"),
    CODE_500(Integer.valueOf(500), "message.common.servererror"),

    CODE_1001(Integer.valueOf(1001), "该道具已经激活过"),
    CODE_1002(Integer.valueOf(1002), "同类型的道具只能同时使用一个"),


    CODE_2001(Integer.valueOf(2001), "用户扩展信息缺失"),

    CODE_3001(Integer.valueOf(3001), "等级配置信息缺失"),
    CODE_4001(Integer.valueOf(4001), "今日已达到捡金币次数上限"),
    CODE_4002(Integer.valueOf(4002), "同一个好友一天只能PK一次");

    private Integer code;
    private String value;

    private ReturnCodeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public final Integer getCode() {
        return this.code;
    }



    public final String getValue() {
        return this.value;
    }

    public String toString() {
        return this.code + "=" + this.value;
    }
}
