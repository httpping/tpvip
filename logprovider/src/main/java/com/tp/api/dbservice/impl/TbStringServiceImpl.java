package com.tp.api.dbservice.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tp.api.dbservice.DbTbStringService;
import com.tp.api.entity.TbString;
import com.tp.api.mapper.TbStringMapper;
import com.tp.api.mode.StringFilterRequestParam;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class TbStringServiceImpl extends ServiceImpl<TbStringMapper, TbString> implements DbTbStringService {


    @Override
    public TbString saveOrUpdate(TbString tbString) {

        tbString.setVersion(1);
        Condition condition =  Condition.create();
        if (!StringUtils.isEmpty(tbString.getName())) {
            condition.where("name = {0}", tbString.getName());
        }
        if (!StringUtils.isEmpty(tbString.getDomain())) {
            condition.and("domain = {0}", tbString.getDomain());
        }
        List<TbString> tbStrings = baseMapper.selectList(condition);

        if (tbStrings !=null && tbStrings.size() == 1){
            TbString result =  tbStrings.get(0);
            tbString.setId(result.getId());
            tbString.setVersion(result.getVersion() + 1);
            tbString.setAppVersion(result.getAppVersion());
        }

        //最新加入的放前面
        insertOrUpdate(tbString);


        return tbString;
    }

    @Override
    public Page<TbString> select(StringFilterRequestParam request) {

        EntityWrapper condition =  new EntityWrapper();
        if (!StringUtils.isEmpty(request.getDomain())) {
            condition.where("domain = {0}", request.getDomain());
        }
        if (!StringUtils.isEmpty(request.getAppVersion())) {
            condition.where("app_version = {0}", request.getAppVersion());
        }
        if (!StringUtils.isEmpty(request.getFlag())) {
            condition.where("flag = {0}", request.getFlag());
        }

        if (!StringUtils.isEmpty(request.getSort())) {

            if ("asc".equals(request.getOrder())) {
                condition.orderBy(request.getSort(),true);
            }else {
                condition.orderBy(request.getSort(),false);
            }
        }
        condition.orderBy("version");

        Page page = new Page<TbString>(request.getOffset()/request.getLimit(), request.getLimit());

        EntityWrapper<StringFilterRequestParam> entityWrapper = new EntityWrapper<>();

        List<TbString> tbStrings = baseMapper.selectPage(page,condition);
        page.setRecords(tbStrings);
        return page;

    }

    @Override
    public List<TbString> groupBy(String name) {

        Condition condition =  Condition.create();
        condition.groupBy(name);
        List<TbString> groups = baseMapper.selectList(condition);

        return groups;
    }

    @Override
    public boolean saveOrUpdateList(List<TbString> tbStrings) {

        for (TbString tb : tbStrings) {
             saveOrUpdate(tb);
        }

        return true;
    }
}
