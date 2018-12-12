package com.tp.api.dbservice;

import com.tp.api.entity.ApkInfo;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanping
 * @since 2018-12-11
 */
public interface ApkInfoService extends IService <ApkInfo> {
    /**
    * 添加方法
    * @return
    */
    public ApkInfo  add(ApkInfo model);
    /**
    * 删除
    * @return
    */
    public ApkInfo  delete(ApkInfo model);
    /**
    * 修改方法
    * @return
    */
    public ApkInfo  update(ApkInfo model);
    /**
    * 单个查询
    * @return
    */
    public List<ApkInfo> query(ApkInfo model);

    /**
    * 条件查询分页列表
    * @param pageParam
    * @return
    */
    public Page page(ApkInfo pageParam);


    /**
     * 分组 apk
     * @return
     */
    public List<ApkInfo> apkGroup();



}
