package com.tp.api.dbservice.impl;

import com.tp.api.dbservice.ApkInfoService;
import com.tp.api.entity.ApkInfo;
import com.tp.api.entity.TbLog;
import com.tp.api.mapper.ApkInfoMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanping
 * @since 2018-12-11
 */
@Service
public class ApkInfoServiceImpl extends ServiceImpl <ApkInfoMapper, ApkInfo> implements ApkInfoService {
        /**
        * 添加方法
        * @return
        */
        @Override
        public ApkInfo  add(ApkInfo model){
            if (model.getUpdateTime() == null){
                model.setUpdateTime(new Date());
                model.setCreatedTime(new Date());
            }
            boolean back=this.insertOrUpdate(model);
            return model;
        }
        /**
        * 删除
        * @return
        */
        @Override
        public ApkInfo  delete(ApkInfo model){
            boolean back=this.deleteById(model.getId());
            return model;
        }
        /**
        * 修改方法
        * @return
        */
        @Override
        public ApkInfo  update(ApkInfo model){
            boolean back=this.updateById(model);
            return model;
        }
        /**
        * 单个查询
        * @return
        */
        @Override
        public List<ApkInfo> query(ApkInfo model){
            Condition condition =  Condition.create();
            if (!StringUtils.isEmpty(model.getName())){
                condition.where("name={0}",model.getName());
            }
            condition.orderBy("update_time",false);
            List<ApkInfo> logs = baseMapper.selectList(condition);

            return logs;
        }

        /**
        * 条件查询分页列表
        * @param pageParam
        * @return
        */
        @Override
        public Page page(ApkInfo pageParam){
            EntityWrapper condition =  new EntityWrapper();
            Page page = new Page<ApkInfo>(0, 20);
            List<ApkInfo> tbStrings = baseMapper.selectPage(page,condition);
            page.setRecords(tbStrings);
            return page;
        }

    @Override
    public List<ApkInfo> apkGroup() {
        return baseMapper.apkGroup();
    }


}
