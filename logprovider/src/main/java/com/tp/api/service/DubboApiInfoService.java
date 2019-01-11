package com.tp.api.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.tp.api.dbservice.ApkInfoService;
import com.tp.api.entity.ApkInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * dubbo 请求
 */
@Service(filter = "tracing")
public class DubboApiInfoService implements ApkInfosService {

    @Autowired
    ApkInfoService apkInfoService;

    @Override
    public ApkInfo add(ApkInfo model) {
        return apkInfoService.add(model);
    }

    @Override
    public ApkInfo delete(ApkInfo model) {
        return apkInfoService.delete(model);
    }

    @Override
    public ApkInfo update(ApkInfo model) {
        return apkInfoService.update(model);
    }

    @Override
    public List<ApkInfo> query(ApkInfo model) {
        return apkInfoService.query(model);
    }

    @Override
    public Page page(ApkInfo pageParam) {
        return apkInfoService.page(pageParam);
    }

    @Override
    public List<ApkInfo> apkGroup() {
        return apkInfoService.apkGroup();
    }
}
