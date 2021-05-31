package com.player.game.servers;

import org.apache.ibatis.session.SqlSession;

import com.player.framework.net.IdSession;
import com.player.framework.net.MessageRouter;
import com.player.framework.net.PlayerSessionManager;
import com.player.framework.net.PropertySession;
import com.player.framework.orm.OrmFactory;
import com.player.game.Resonpose;
import com.player.game.mappers.PlayerMapper;
import com.player.game.messages.player.PlayerInfoComponent;
import com.player.game.messages.player.ReqSelectPlayer;
import com.player.game.messages.player.ResSelectPlayer;
import com.player.game.models.PlayerModel;

public class PlayerServer {

	public static PlayerServer playerServer = new PlayerServer();

	public static PlayerServer getInstance() {
		return playerServer;
	}

	public void reqSelectPlayer(IdSession session, ReqSelectPlayer request) {
		ResSelectPlayer res = new ResSelectPlayer();
		Object obj = session.getAttribute(PropertySession.UID);
		if (obj == null) {
			res.status = Resonpose.InvalidOpt;
			MessageRouter.send(session, res);
			return;
		}
		PlayerModel player;
		long uid = (long) obj;
		long playerId = session.getPlayerId();
		SqlSession sqlSession = OrmFactory.INSTANCE.getSqlSession();
		PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
		if (playerId == 0) {
			player = playerMapper.getPlayerByUid(uid);
			if (player == null) {
				player = new PlayerModel();
				player.setUid(uid);
				player.setName(request.name);
				player.setJob(request.job);
				player.setPlatform(request.platform);
				playerMapper.add(player);
			}
		} else {
			player = playerMapper.getPlayerById(playerId);
		}
		sqlSession.commit();
		sqlSession.close();
		PlayerSessionManager.INSTANCE.setPlayerSession(playerId, session);
		res.status = Resonpose.OK;
		res.playerInfo = new PlayerInfoComponent();
		MessageRouter.send(session, res);
	}

}
