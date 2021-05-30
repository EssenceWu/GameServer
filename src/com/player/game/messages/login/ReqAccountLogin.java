package com.player.game.messages.login;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.player.framework.serializer.Message;
import com.player.framework.serializer.annotation.MessageMeta;
import com.player.game.Cmd;
import com.player.game.Module;

@MessageMeta(module = Module.BASE, cmd = Cmd.ResGuestLogin)
public class ReqAccountLogin extends Message {

	@Protobuf(order = 1)
	public long accountId;

	@Protobuf(order = 2)
	public String password;

}
