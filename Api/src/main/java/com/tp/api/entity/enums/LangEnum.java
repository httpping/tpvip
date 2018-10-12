package com.tp.api.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 运营商联系电话枚举
 */
public enum LangEnum implements IEnum {
    EN("en", "英语"),
    ES("es", "西班牙"),
    AR("ar", "沙特阿拉伯"),
    DE("de","德语"),
    FR("fr", "法语");

    private String value;
    private String desc;

    LangEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }


}
