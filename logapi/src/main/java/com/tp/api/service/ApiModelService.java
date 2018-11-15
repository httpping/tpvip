package com.tp.api.service;

import com.tp.api.entity.ApiModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanping
 * @since 2018-09-21
 */
public interface ApiModelService {

    List<ApiModel> findAll(ApiModel request);

    ApiModel findById(ApiModel request);

    ApiModel save(ApiModel apiModel);

    ApiModel selectByUrl(String url);
}