package com.player.framework.net;

import java.util.HashMap;
import java.util.Map;

import com.player.framework.serializer.Message;

import io.netty.channel.Channel;

public class PlayerSession implements IdSession {

	private Channel channel;
	private Map<String, Object> Storage = new HashMap<>();

	public PlayerSession(Channel channel) {
		super();
		this.channel = channel;
	}

	public long getPlayerId() {
		if (Storage.containsKey(PropertySession.PLAYER_ID)) {
			return (long) Storage.get(PropertySession.PLAYER_ID);
		}
		return 0;
	}

	public Object getAttribute(String key) {
		return Storage.get(key);
	}

	public void setAttribute(String key, Object value) {
		Storage.put(key, value);
	}

	public void send(Message message) {
		channel.writeAndFlush(message);
	}

}
