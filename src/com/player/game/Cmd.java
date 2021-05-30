package com.player.game;

public interface Cmd {

	int INVALID_CMD = 0;

	// 游客登录
	int ReqGuestLogin = 1;
	int ResGuestLogin = 2;

	// 正式账号登录
	int ReqUserLogin = 3;
	int ResUserLogin = 4;

}
