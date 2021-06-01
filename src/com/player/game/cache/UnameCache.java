package com.player.game.cache;

import com.player.framework.cache.CacheAdapter;
import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.UserMapper;
import com.player.game.models.UserModel;

public class UnameCache extends CacheAdapter<String, UserModel> {

	public static UnameCache unameCache = new UnameCache();

	public static UserModel get(String uname) {
		return unameCache.getCache(uname);
	}

	public UserModel loadCache(String uname) {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(UserMapper.class);
		UserMapper userMapper = UserMapper.class.cast(mapper.getObject());
		UserModel userModel = userMapper.getUserByUname(uname);
		mapper.close();
		return userModel;
	}

}
