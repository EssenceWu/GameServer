package com.player.game.messages.login;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.player.framework.serializer.Message;
import com.player.framework.serializer.annotation.MessageMeta;
import com.player.game.Module;
import com.player.game.Cmd;

@MessageMeta(module = Module.LOGIN, cmd = Cmd.ReqUserLogin)
public class ReqUserLogin extends Message {
	@Protobuf(order = 1, required = true)
	public String uname;

	@Protobuf(order = 2)
	public String upwd;

}
