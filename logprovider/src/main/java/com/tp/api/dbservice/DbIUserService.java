package com.tp.api.dbservice;



/*
*
 *
 * User 表数据服务层接口
 *
*/


import com.baomidou.mybatisplus.service.IService;
import com.tp.api.entity.User;

import java.util.List;

public interface DbIUserService extends IService<User> {

	boolean deleteAll();

	public List<User> selectListBySQL();
}
