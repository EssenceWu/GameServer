package com.player.game.messages.player;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class RuckSackItem {
	@Protobuf(order = 1, required = true)
	public int itemType;

	@Protobuf(order = 2, required = true)
	public int count;
}
