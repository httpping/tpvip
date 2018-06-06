package com.tp.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tp.api.entity.User;
import com.tp.api.mapper.UserMapper;
import com.tp.api.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * User 表数据服务层接口实现类
 *
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<User> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

}
