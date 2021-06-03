package com.player.game.messages.player;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.player.framework.annotation.message.MessageMeta;
import com.player.framework.serializer.Message;
import com.player.game.Module;
import com.player.game.Cmd;

@MessageMeta(module = Module.PLAYER, cmd = Cmd.ResSelectPlayer)
public class ResSelectPlayer extends Message {
	@Protobuf(order = 1, required = true)
	public int status;

	@Protobuf(order = 2, required = true)
	public PlayerInfo playerInfo;

	@Protobuf(order = 3, required = true)
	public Equipment eProp;
}
