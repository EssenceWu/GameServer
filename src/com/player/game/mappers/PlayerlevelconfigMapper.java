package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Playerlevelconfig;

@DataSource
public interface PlayerlevelconfigMapper {
	int insert(Playerlevelconfig record);

	int insertSelective(Playerlevelconfig record);

	List<Playerlevelconfig> getList();
}