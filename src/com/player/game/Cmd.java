package com.player.game;

public interface Cmd {

	int INVALID_CMD = 0;

	// �ο͵�¼
	int ReqGuestLogin = 1;
	int ResGuestLogin = 2;

	// ��ʽ�˺ŵ�¼
	int ReqUserLogin = 3;
	int ResUserLogin = 4;

}
