package com.player.framework.net;

import java.util.Collection;
import com.player.framework.serializer.Message;

public class MessageRouter {

	public static void send(long playerId, Message message) {
		IdSession session = PlayerSessionManager.INSTANCE.getPlayerSession(playerId);
		send(session, message);
	}

	public static void send(Collection<Long> playerIds, Message message) {
		for (long playerId : playerIds) {
			send(playerId, message);
		}
	}

	public static void send(IdSession session, Message message) {
		if (session == null || message == null) {
			return;
		}
		session.send(message);
	}

}
