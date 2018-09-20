package com.tp.api.service;

import com.tp.api.entity.TbLog;
import com.baomidou.mybatisplus.service.IService;
import com.tp.api.mode.LogRequest;
import com.tp.api.mode.LoggerMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanping
 * @since 2018-09-19
 */
public interface TbLogService extends IService<TbLog> {

    TbLog save(TbLog tbLog);

    List<TbLog> findAll(TbLog request);

    boolean deleteAll(LoggerMessage request);
}
