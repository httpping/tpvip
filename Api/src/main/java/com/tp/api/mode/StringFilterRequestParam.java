package com.tp.api.mode;


import lombok.Data;

@Data
public class StringFilterRequestParam {

    public String domain;
    public Integer flag ;
    public Integer version ;

    public String appVersion ;
}
