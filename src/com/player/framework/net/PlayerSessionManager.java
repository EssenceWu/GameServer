package com.player.framework.net;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum PlayerSessionManager {

	INSTANCE;

	private ConcurrentMap<Long, IdSession> Storage = new ConcurrentHashMap<>();

	public IdSession getSessionByPlayerId(long playerId) {
		return this.Storage.get(playerId);
	}

	public long getPlayerIdBySession(IdSession session) {
		if (session != null) {
			return session.getPlayerId();
		}
		return 0;
	}

	public void setPlayerSession(long playerId, IdSession session) {
		session.setAttribute(SessionProperty.PLAYER_ID, playerId);
		this.Storage.put(playerId, session);
	}

}
