package com.tp.api.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanping
 * @since 2018-10-19
 */
public class TbAnalysisLog extends Model<TbAnalysisLog> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 平台
     */
    private String domain;
    /**
     * 总数量
     */
    private Integer count;
    /**
     * 手机型号
     */
    private String phone;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 天
     */
    private String day;
    /**
     * 小时  
     */
    private String hour;
    private String month;
    /**
     * 月天
     */
    private String monthDay;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TbAnalysisLog{" +
        ", id=" + id +
        ", domain=" + domain +
        ", count=" + count +
        ", phone=" + phone +
        ", updateDate=" + updateDate +
        ", day=" + day +
        ", hour=" + hour +
        ", month=" + month +
        ", monthDay=" + monthDay +
        "}";
    }
}
