package com.tp.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanping
 * @since 2018-09-19
 */
@Data
public class TbLog extends Model<TbLog> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求参数
     */
    private String request;
    /**
     * 返回参数
     */
    private String response;
    /**
     * 接口名称
     */
    private String apiname;
    /**
     * 对应的站
     */
    private String domain;
    /**
     * 版本
     */
    private String version;
    /**
     * 平台
     */
    private String platform;


    private int feeTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    Date updateTime;
    Date createdTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
