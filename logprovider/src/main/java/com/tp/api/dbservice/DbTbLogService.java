package com.tp.api.dbservice;

import com.tp.api.entity.TbLog;
import com.baomidou.mybatisplus.service.IService;
import com.tp.api.entity.TbString;
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
public interface DbTbLogService extends IService<TbLog> {

    TbLog save(TbLog tbLog);

    List<TbLog> findAll(TbLog request);

    boolean deleteAll(LoggerMessage request);

    void ticket(TbLog request);


    TbLog findByUrl(TbLog request);

    List<TbLog> groupBy(String name);




}
