package com.example.springboot.webflux.webfluxservice;

import cn.hutool.core.map.MapUtil;

import com.example.springboot.webflux.entity.User;
import com.example.springboot.webflux.mapper.UserMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * UserWebflux 实现类
 * @author SkyEgine
 */
@Service
public class UserWebFluxServiceImpl implements UserWebFluxService {
	@Resource
	private UserMapper userMapper;
	@Override
	public Mono<Integer> saveUser(User user) {
		return Mono.create(userSink -> userSink.success(userMapper.insert(user)));
	}

	@Override
	public Mono<User> getUserById(Integer id) {
		return Mono.justOrEmpty(userMapper.selectById(id));
	}

	@Override
	public Flux<User> getUserByCond(User user) {
		Map<String, Object> columnMap = MapUtil.newHashMap();
		List<User> users = userMapper.selectByMap(columnMap);
		return Flux.fromIterable(users);
	}

	@Override
	public Mono<Integer> deleteUser(Integer id) {
		return Mono.create(userSink -> userSink.success(userMapper.deleteById(id)));
	}

}
