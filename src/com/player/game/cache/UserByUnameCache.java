package com.player.game.cache;

import com.player.framework.cache.CacheAdapter;
import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.UserMapper;
import com.player.game.models.User;

public class UserByUnameCache extends CacheAdapter<String, User> {

	public static UserByUnameCache userByUnameCache = new UserByUnameCache();

	public static User get(String uname) {
		return userByUnameCache.getCache(uname);
	}

	public User loadCache(String uname) {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(UserMapper.class);
		UserMapper userMapper = UserMapper.class.cast(mapper.getObject());
		User user = userMapper.getUserByUname(uname);
		mapper.close();
		return user;
	}

}
