package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Monestconfig;

@DataSource
public interface MonestconfigMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Monestconfig record);

	int insertSelective(Monestconfig record);

	List<Monestconfig> getList();

	int updateByPrimaryKeySelective(Monestconfig record);

	int updateByPrimaryKey(Monestconfig record);
}