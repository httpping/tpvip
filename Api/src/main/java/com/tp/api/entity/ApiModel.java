package com.tp.api.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanping
 * @since 2018-09-21
 */
@Data
public class ApiModel extends Model<ApiModel> {

    private static final long serialVersionUID = 1L;

    private Long id ;
    private String url;
    private String response;
    private String request;
    private Date createdTime;
    private Date updateTime;
    private String createdBy;
    private String domain;
    private String platform;
    private String apiname;


    @Override
    protected Serializable pkVal() {
        return this.url;
    }


}
