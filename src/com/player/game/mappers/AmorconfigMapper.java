package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Amorconfig;

@DataSource
public interface AmorconfigMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Amorconfig record);

	int insertSelective(Amorconfig record);

	List<Amorconfig> getList();

	int updateByPrimaryKeySelective(Amorconfig record);

	int updateByPrimaryKey(Amorconfig record);
}