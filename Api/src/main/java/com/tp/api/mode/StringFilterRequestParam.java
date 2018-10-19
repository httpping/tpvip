package com.tp.api.mode;


import lombok.Data;

@Data
public class StringFilterRequestParam {

    public String domain;
    public Integer flag ;
    public Integer version ;

    private String order;
    private String sort;
    private int offset;
    private int limit ;

    private String lang;

    public String appVersion ;
}
