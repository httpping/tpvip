package com.tp.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanping
 * @since 2018-10-11
 */
@Data
public class TbString extends Model<TbString> {

    private static final long serialVersionUID = 1L;

    private Long id ;
    /**
     * 命名
     */
    private String name;
    /**
     * 英文
     */
    private String valueEn;
    /**
     * 阿拉伯
     */
    private String valueAr;
    /**
     * 法语
     */
    private String valueFr;
    /**
     * 西班牙
     */
    private String valueEs;
    /**
     * 台湾
     */
    private String valueTw;


/*
    @TableField(exist = false)
    private String lang;*/


    @Override
    protected Serializable pkVal() {
        return this.name;
    }

}
