package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Potionconfig;

@DataSource
public interface PotionconfigMapper {

	List<Potionconfig> getList();
}