package com.tp.api.mapper;

import com.tp.api.entity.TbLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tanping
 * @since 2018-09-19
 */
public interface TbLogMapper extends BaseMapper<TbLog> {


    TbLog findByUrl(@Param("url") String url);


    List<TbLog> findAll();
}
