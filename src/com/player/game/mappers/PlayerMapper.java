package com.player.game.mappers;

import com.player.framework.annotation.database.UserSource;
import com.player.game.models.Player;

@UserSource
public interface PlayerMapper {

	public Player getPlayerByUid(long uid);

	public void add(Player playerModel);

}
