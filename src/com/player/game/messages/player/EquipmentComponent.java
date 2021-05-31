package com.player.game.messages.player;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class EquipmentComponent {
	@Protobuf(order = 1, required = true)
	public int weaponType;

	@Protobuf(order = 2, required = true)
	public int amorType;
}
