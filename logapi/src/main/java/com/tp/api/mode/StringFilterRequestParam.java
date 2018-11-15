package com.tp.api.mode;


import lombok.Data;

import java.io.Serializable;

@Data
public class StringFilterRequestParam implements Serializable {

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
