package com.tp.api.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tp.api.entity.TbString;
import com.tp.api.mapper.TbStringMapper;
import com.tp.api.service.TbStringService;
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
public class TbStringServiceImpl extends ServiceImpl<TbStringMapper, TbString> implements TbStringService {


    @Override
    public TbString saveOrUpdate(TbString tbString) {

        Condition condition =  Condition.create();
        if (!StringUtils.isEmpty(tbString.getName())) {
            condition.where("name = {0}", tbString.getName());
        }
        List<TbString> tbStrings = baseMapper.selectList(condition);

        if (tbStrings !=null && tbStrings.size() == 1){
            TbString result =  tbStrings.get(0);
            tbString.setId(result.getId());
        }

        insertOrUpdate(tbString);


        return null;
    }
}
