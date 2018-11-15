package com.tp.api.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.tp.api.dbservice.DbApiModelService;
import com.tp.api.entity.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DubboApiModelService implements ApiModelService{

    @Autowired
    DbApiModelService dbApiModelService;

    @Override
    public List<ApiModel> findAll(ApiModel request) {
        return dbApiModelService.findAll(request);
    }

    @Override
    public ApiModel findById(ApiModel request) {
        return dbApiModelService.findById(request);
    }

    @Override
    public ApiModel save(ApiModel apiModel) {
        return dbApiModelService.save(apiModel);
    }

    @Override
    public ApiModel selectByUrl(String url) {
        return dbApiModelService.selectByUrl(url);
    }
}
