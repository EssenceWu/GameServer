package com.player.game.cache;

import com.player.framework.cache.CacheAdapter;
import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.UserMapper;
import com.player.game.models.User;

public class UnameCache extends CacheAdapter<String, User> {

	public static UnameCache unameCache = new UnameCache();

	public static User get(String uname) {
		return unameCache.getCache(uname);
	}

	public User loadCache(String uname) {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(UserMapper.class);
		UserMapper userMapper = UserMapper.class.cast(mapper.getObject());
		User user = userMapper.getUserByUname(uname);
		mapper.close();
		return user;
	}

}
