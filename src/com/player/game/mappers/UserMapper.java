package com.player.game.mappers;

import com.player.game.models.UserModel;

public interface UserMapper {

	public UserModel getUserbyGuestKey(String guestKey);

	public UserModel getUserByUname(String uname);

	public int addUser(UserModel user);

}
