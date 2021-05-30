package com.player.game.messages.login;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.player.framework.serializer.Message;
import com.player.framework.serializer.annotation.MessageMeta;
import com.player.game.Module;
import com.player.game.Cmd;

@MessageMeta(module = Module.LOGIN, cmd = Cmd.ReqGuestLogin)
public class ReqGuestLogin extends Message {

	@Protobuf(order = 1)
	public String guestKey;

}