package com.tp.api.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanping
 * @since 2018-12-11
 */
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ApkInfo{" +
        ", id=" + id +
        ", name=" + name +
        ", number=" + number +
        ", fileName=" + fileName +
        ", path=" + path +
        ", size=" + size +
        ", emails=" + emails +
        ", changeLog=" + changeLog +
        ", duration=" + duration +
        ", updateTime=" + updateTime +
        ", createdTime=" + createdTime +
        ", status=" + status +
        "}";
    }
}
