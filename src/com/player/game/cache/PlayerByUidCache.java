package com.player.game.cache;

import com.player.framework.cache.CacheAdapter;
import com.player.framework.net.IdSession;
import com.player.framework.net.PropertySession;
import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmAsyncFactory;
import com.player.framework.orm.OrmFactory;
import com.player.framework.util.ToolUtil;
import com.player.game.mappers.PlayerMapper;
import com.player.game.messages.player.ReqSelectPlayer;
import com.player.game.models.Player;

public class PlayerByUidCache extends CacheAdapter<Long, Player> {

	public static PlayerByUidCache playerByUidCache = new PlayerByUidCache();

	public static Player get(long uid) {
		return playerByUidCache.getCache(uid);
	}

	public static Player add(IdSession session, long uid, ReqSelectPlayer request) {
		Player player = new Player();
		player.setId(ToolUtil.getId());
		player.setUid(uid);
		player.setName(request.name);
		player.setJob(request.job);
		player.setExp(0);
		player.setPlatform(request.platform);
		int uuid = (int) session.getAttribute(PropertySession.UUID);
		OrmAsyncFactory.notify(uuid, PlayerMapper.class, "add", player);
		return player;
	}

	public Player loadCache(Long uid) {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(PlayerMapper.class);
		PlayerMapper playerMapper = PlayerMapper.class.cast(mapper.getObject());
		Player playerModel = playerMapper.getPlayerByUid(uid.longValue());
		mapper.close();
		return playerModel;
	}

}
