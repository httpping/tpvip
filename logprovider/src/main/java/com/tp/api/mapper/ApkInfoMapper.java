package com.tp.api.mapper;

import com.tp.api.entity.ApkInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tanping
 * @since 2018-12-11
 */
public interface ApkInfoMapper extends BaseMapper<ApkInfo> {

    List<ApkInfo> apkGroup() ;

}
