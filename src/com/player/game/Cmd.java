package com.player.game;

public interface Cmd {

	int INVALID_CMD = 0;

	// 游客登录
	int ReqGuestLogin = 1;
	int ResGuestLogin = 2;

	// 正式账号登录
	int ReqUserLogin = 3;
	int ResUserLogin = 4;

	// 选择职业
	int ReqSelectPlayer = 5;
	int ResSelectPlayer = 6;

	// 玩家背包
	int ReqRuckSack = 7;
	int ResRuckSack = 8;

}
