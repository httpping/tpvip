package com.tp.api.mode;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupByRequest implements Serializable{

    public String group;
}
