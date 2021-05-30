package com.player.game.messages.login;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class AccountInfo {

	@Protobuf(order = 1, required = true)
	public String unick;

}
