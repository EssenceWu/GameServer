package com.player.game.mappers;

import com.player.framework.annotation.database.UserSource;
import com.player.game.models.User;

@UserSource
public interface UserMapper {

	public User getUserbyGuestKey(String guestKey);

	public User getUserByUname(String uname);

	public void add(User user);

}
