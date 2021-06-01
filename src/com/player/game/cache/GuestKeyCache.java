package com.player.game.cache;

import com.player.framework.cache.CacheAdapter;
import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.UserMapper;
import com.player.game.models.UserModel;

public class GuestKeyCache extends CacheAdapter<String, UserModel> {

	public static GuestKeyCache userGuestKeyCache = new GuestKeyCache();

	public static UserModel getUserbyGuestKey(String guestKey) {
		return userGuestKeyCache.get(guestKey);
	}

	public UserModel select(String guestKey) {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(UserMapper.class);
		UserMapper userMapper = UserMapper.class.cast(mapper.getObject());
		UserModel userModel = userMapper.getUserbyGuestKey(guestKey);
		mapper.close();
		return userModel;
	}

}
