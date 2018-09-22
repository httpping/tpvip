package com.tp.api.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tp.api.entity.ApiModel;
import com.tp.api.mapper.ApiModelMapper;
import com.tp.api.service.ApiModelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanping
 * @since 2018-09-21
 */
@Service
public class ApiModelServiceImpl extends ServiceImpl<ApiModelMapper, ApiModel> implements ApiModelService {

    @Override
    public List<ApiModel> findAll(ApiModel request) {

        Condition condition =  Condition.create();
        if (!StringUtils.isEmpty(request.getUrl())){
            condition.where("url like {0}", "%" + request.getUrl().toLowerCase() + "%");
        }
        if (!StringUtils.isEmpty(request.getDomain())){
            condition.and("domain = {0}",request.getDomain());
        }
        if (!StringUtils.isEmpty(request.getCreatedBy())){
            condition.and("created_by = {0}",request.getCreatedBy());
        }
        List desc = new ArrayList();
        desc.add("update_time");
        condition.orderDesc(desc);
        List<ApiModel> result = baseMapper.selectList(condition);

        return result;
    }

    @Override
    public ApiModel findById(ApiModel request) {
        return selectById(request.getId());
    }

    @Override
    public ApiModel save(ApiModel request) {

        if (request.getId() == null) {
            Condition condition = Condition.create();
            if (!StringUtils.isEmpty(request.getUrl())) {
                condition.where("url = {0}", request.getUrl().toLowerCase());
            }
            List<ApiModel> apiModels = baseMapper.selectList(condition);

            ApiModel result = request;
            if (apiModels!=null && apiModels.size()>0){
                result = apiModels.get(0);
                result.setResponse(request.getResponse());

                if (!StringUtils.isEmpty(request.getPlatform())){
                    result.setPlatform(request.getPlatform());
                }
                if (!StringUtils.isEmpty(request.getDomain())){
                    result.setDomain(request.getDomain());
                }

                if (!StringUtils.isEmpty(request.getApiname())){
                    result.setApiname(request.getApiname());
                }
                if (!StringUtils.isEmpty(request.getDomain())){
                    result.setDomain(request.getDomain());
                }

            }else {
                result.setCreatedTime(new Date());
            }
            request.setId(result.getId());
            request.setUrl(request.getUrl().toLowerCase());
            insertOrUpdate(result);
            request= result;
        }else{
            insertOrUpdate(request);
        }



        return request;
    }



    public ApiModel selectByUrl(String url) {
        url = url.toLowerCase();
        Condition condition =  Condition.create();
        if (!StringUtils.isEmpty(url)){
            condition.where("url = {0}", url);
        }
        List<ApiModel> apiModels =  baseMapper.selectList(condition);
        ApiModel result = null;

        if (apiModels!=null && apiModels.size()>0) {
            result = apiModels.get(0);
        }

        return result;
    }
}
