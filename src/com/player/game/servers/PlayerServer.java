package com.player.game.servers;

import com.player.framework.net.IdSession;
import com.player.framework.net.MessageRouter;
import com.player.framework.net.PlayerSessionManager;
import com.player.framework.net.PropertySession;
import com.player.game.Resonpose;
import com.player.game.cache.UidCache;
import com.player.game.messages.player.PlayerInfo;
import com.player.game.messages.player.ReqSelectPlayer;
import com.player.game.messages.player.ResSelectPlayer;
import com.player.game.models.Player;

public class PlayerServer {

	public static PlayerServer playerServer = new PlayerServer();

	public static PlayerServer getInstance() {
		return playerServer;
	}

	public void reqSelectPlayer(IdSession session, ReqSelectPlayer request) {
		ResSelectPlayer res = new ResSelectPlayer();
		Object object = session.getAttribute(PropertySession.UID);
		if (object == null) {
			res.status = Resonpose.InvalidOpt;
			MessageRouter.send(session, res);
			return;
		}
		long uid = (long) object;
		Player player = UidCache.get(uid);
		if (player == null) {
			player = UidCache.add(session, uid, request);
		}
		PlayerSessionManager.INSTANCE.setPlayerSession(player.getId(), session);
		res.status = Resonpose.OK;
		res.playerInfo = new PlayerInfo();
		MessageRouter.send(session, res);
	}

}
