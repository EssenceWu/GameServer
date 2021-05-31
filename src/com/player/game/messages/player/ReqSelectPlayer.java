package com.player.game.messages.player;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.player.framework.annotation.message.MessageMeta;
import com.player.framework.serializer.Message;
import com.player.game.Cmd;
import com.player.game.Module;

@MessageMeta(module = Module.PLAYER, cmd = Cmd.ReqSelectPlayer)
public class ReqSelectPlayer extends Message {
	@Protobuf(order = 1, required = true)
	public String name;

	@Protobuf(order = 2, required = true)
	public int job;

	@Protobuf(order = 3, required = true)
	public String platform;
}
