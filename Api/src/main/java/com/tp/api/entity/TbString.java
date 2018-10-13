package com.tp.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

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

    /**
     * 德语
     */
    private String valueDe;


    /**
     * 印度尼西亚语
     */
    private String valueId;



    private String domain = "zaful";

    /**
     * 1 删除
     */
    private Integer flag ;



    private String appVersion ;
    private Date updateTime;

    private Integer version;

    private Integer ticket;

/*
    @TableField(exist = false)
    private String lang;*/


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
