package com.tp.api.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanping
 * @since 2018-12-11
 */

@Data
public class ApkInfo extends Model<ApkInfo> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 项目构建名
     */
    private String name;
    /**
     * 构建版本
     */
    private Integer number;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件大小
     */
    private Integer size;
    /**
     * 邮箱
     */
    private String emails;
    /**
     * 改变日志
     */
    @TableField("changeLog")
    private String changeLog;
    /**
     * 打包花费时间
     */
    private Long duration;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 打包状态
     */
    private Integer status;


    private String plistUrl;

    private Integer platform;


    @TableField(exist = false)
    private String appBundle;
    @TableField(exist = false)
    private String appVersion;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
