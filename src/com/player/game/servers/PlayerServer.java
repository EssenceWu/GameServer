package com.player.game.servers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.player.framework.net.IdSession;
import com.player.framework.net.MessageRouter;
import com.player.framework.net.PlayerSessionManager;
import com.player.framework.net.PropertySession;
import com.player.game.Resonpose;
import com.player.game.cache.PlayerByUidCache;
import com.player.game.cache.RucksackitemByPlayerIdCache;
import com.player.game.messages.player.PlayerInfo;
import com.player.game.messages.player.ReqRuckSack;
import com.player.game.messages.player.ReqSelectPlayer;
import com.player.game.messages.player.ResRuckSack;
import com.player.game.messages.player.ResSelectPlayer;
import com.player.game.messages.player.RuckSackItem;
import com.player.game.models.Player;
import com.player.game.models.Rucksackitem;

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
		Player player = PlayerByUidCache.get(uid);
		if (player == null) {
			player = PlayerByUidCache.add(session, uid, request);
		}
		PlayerSessionManager.INSTANCE.setPlayerSession(player.getId(), session);
		res.status = Resonpose.OK;
		res.playerInfo = new PlayerInfo();
		MessageRouter.send(session, res);
	}

	public void reqRuckSack(IdSession session, ReqRuckSack request) {
		ResRuckSack res = new ResRuckSack();
		long playerId = session.getPlayerId();
		if (playerId <= 0) {
			res.status = Resonpose.InvalidOpt;
			MessageRouter.send(session, res);
			return;
		}
		Map<Integer, Rucksackitem> result = RucksackitemByPlayerIdCache.get(playerId);
		List<RuckSackItem> items = new ArrayList<RuckSackItem>();
		if (!result.isEmpty()) {
			for (Rucksackitem rucksackitem : result.values()) {
				RuckSackItem item = new RuckSackItem();
				item.count = rucksackitem.getCount();
				item.itemType = rucksackitem.getItemid();
				items.add(item);
			}
		}
		res.status = Resonpose.OK;
		res.items = items;
		MessageRouter.send(session, res);
	}

}
