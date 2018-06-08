package com.tp.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tp.api.entity.User;
import com.tp.api.mapper.UserMapper;
import com.tp.api.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * User 表数据服务层接口实现类
 *
 */

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}


	@Cacheable("tp_vip")
	@Override
	public List<User> selectListBySQL() {
		log.info("cache test");
		return baseMapper.selectListBySQL();
	}

}
