package com.player.framework.net;

import com.player.framework.serializer.Message;

public interface IdSession {

	public long getPlayerId();

	public Object getAttribute(String key);

	public void setAttribute(String key, Object value);

	public void send(Message message);

}
