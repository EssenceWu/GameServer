package com.player.game.cache;

import com.player.framework.cache.CacheAdapter;
import com.player.framework.net.IdSession;
import com.player.framework.net.PropertySession;
import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmAsyncFactory;
import com.player.framework.orm.OrmFactory;
import com.player.framework.util.ToolUtil;
import com.player.game.Config;
import com.player.game.mappers.UserMapper;
import com.player.game.models.User;

public class UserByGuestKeyCache extends CacheAdapter<String, User> {

	public static UserByGuestKeyCache userByGuestKeyCache = new UserByGuestKeyCache();

	public static User get(String guestKey) {
		return userByGuestKeyCache.getCache(guestKey);
	}

	public static User add(IdSession session, String guestKey) {
		User user = new User();
		user.setId(ToolUtil.getId());
		user.setUname("сн©м" + ToolUtil.getRandom(1000, 9999));
		user.setUnick(Config.name[ToolUtil.getRandom(0, 9)]);
		user.setGuestKey(guestKey);
		user.setIsGuest(1);
		user.setStatus(1);
		int uuid = (int) session.getAttribute(PropertySession.UUID);
		OrmAsyncFactory.notify(uuid, UserMapper.class, "add", user);
		return user;
	}

	public User loadCache(String guestKey) {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(UserMapper.class);
		UserMapper userMapper = UserMapper.class.cast(mapper.getObject());
		User userModel = userMapper.getUserbyGuestKey(guestKey);
		mapper.close();
		return userModel;
	}

}
