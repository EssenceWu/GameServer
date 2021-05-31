package com.player.game.mappers;

import com.player.game.models.PlayerModel;

public interface PlayerMapper {

	public PlayerModel getPlayerById(long id);

	public PlayerModel getPlayerByUid(long uid);

	public void add(PlayerModel playerModel);

}
