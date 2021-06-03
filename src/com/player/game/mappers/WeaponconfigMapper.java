package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Weaponconfig;

@DataSource
public interface WeaponconfigMapper {

	List<Weaponconfig> getList();
}