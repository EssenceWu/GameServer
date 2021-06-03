package com.player.game.messages.player;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PlayerInfo {
	@Protobuf(order = 1, required = true)
	public int exp;

	@Protobuf(order = 2, required = true)
	public int level;

	@Protobuf(order = 3, required = true)
	public int HP;

	@Protobuf(order = 4, required = true)
	public int MP;

	@Protobuf(order = 5, required = true)
	public int ucoin;

	@Protobuf(order = 6, required = true)
	public int umoney;

	@Protobuf(order = 7, required = true)
	public int job;

	@Protobuf(order = 8, required = true)
	public int attack;

	@Protobuf(order = 9, required = true)
	public int def;

	@Protobuf(order = 10, required = true)
	public int maxHp;

	@Protobuf(order = 11, required = true)
	public int maxMp;

	@Protobuf(order = 12, required = true)
	public float speed;
}
