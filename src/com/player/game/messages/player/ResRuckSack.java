package com.player.game.messages.player;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.player.framework.annotation.message.MessageMeta;
import com.player.framework.serializer.Message;
import com.player.game.Cmd;
import com.player.game.Module;

@MessageMeta(module = Module.PLAYER, cmd = Cmd.ResRuckSack)
public class ResRuckSack extends Message {
	@Protobuf(order = 1, required = true)
	public int status;

	@Protobuf(order = 2, required = true)
	public List<RuckSackItem> items;
}
