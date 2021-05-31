package com.player.game.messages.login;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.player.framework.annotation.message.MessageMeta;
import com.player.framework.serializer.Message;
import com.player.game.Module;
import com.player.game.Cmd;

@MessageMeta(module = Module.LOGIN, cmd = Cmd.ResGuestLogin)
public class ResGuestLogin extends Message {

	@Protobuf(order = 1)
	public int status;

	@Protobuf(order = 2)
	public AccountInfo uinfo;

}
