package com.player.game.mappers;

import com.player.framework.annotation.database.User;
import com.player.game.models.UserModel;

@User
public interface UserMapper {

	public UserModel getUserbyGuestKey(String guestKey);

	public UserModel getUserByUname(String uname);

	public void add(UserModel user);

}
