package com.tp.api.mode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by kl on 2017/10/9.
 * Content :日志消息实体，注意，这里为了减少篇幅，省略了get,set代码
 */
@Data
@ApiModel("日志消息参数")
public class LoggerMessage{


    @ApiModelProperty("小黑窗显示")
    public String body;

    public String timestamp;
    public String threadName;
    public String className;
    public String level;
    public String platform;
    public String userId;

    /**
    * 请求地址
     */

    @ApiModelProperty("请求地址")
    private String url;
    /**
     * 请求参数
     */
    @ApiModelProperty("请求参数")
    private String request;
    /**
     * 返回参数
     */
    @ApiModelProperty("返回参数")
    private String response;
    /**
     * 接口名称
     */
    @ApiModelProperty("返回参数")
    private String apiname;
    /**
     * 对应的站
     */
    @ApiModelProperty("对应的站")
    private String domain;
    /**
     * 版本
     */
    @ApiModelProperty("版本")
    private String version;
    @ApiModelProperty("花费的时间")


    private int feeTime;


    public LoggerMessage() {
    }



}