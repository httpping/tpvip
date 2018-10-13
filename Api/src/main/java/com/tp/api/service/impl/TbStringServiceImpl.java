package com.tp.api.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tp.api.entity.TbLog;
import com.tp.api.entity.TbString;
import com.tp.api.mapper.TbStringMapper;
import com.tp.api.mode.StringFilterRequestParam;
import com.tp.api.service.TbStringService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanping
 * @since 2018-10-11
 */
@Service
public class TbStringServiceImpl extends ServiceImpl<TbStringMapper, TbString> implements TbStringService {


    @Override
    public TbString saveOrUpdate(TbString tbString) {

        tbString.setVersion(1);
        Condition condition =  Condition.create();
        if (!StringUtils.isEmpty(tbString.getName())) {
            condition.where("name = {0}", tbString.getName());
        }
        List<TbString> tbStrings = baseMapper.selectList(condition);

        if (tbStrings !=null && tbStrings.size() == 1){
            TbString result =  tbStrings.get(0);
            tbString.setId(result.getId());
            tbString.setVersion(result.getVersion() + 1);
        }

        //最新加入的放前面
        insertOrUpdate(tbString);


        return tbString;
    }

    @Override
    public List<TbString> select(StringFilterRequestParam request) {

        Condition condition =  Condition.create();
        if (!StringUtils.isEmpty(request.getDomain())) {
            condition.where("domain = {0}", request.getDomain());
        }
        if (!StringUtils.isEmpty(request.getAppVersion())) {
            condition.where("app_version = {0}", request.getAppVersion());
        }

        condition.orderBy("version");

        List<TbString> tbStrings = baseMapper.selectList(condition);
        return tbStrings;

    }

    @Override
    public boolean saveOrUpdateList(List<TbString> tbStrings) {

        for (TbString tb : tbStrings) {
             saveOrUpdate(tb);
        }

        return true;
    }
}
