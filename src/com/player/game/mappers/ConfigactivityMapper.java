package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Configactivity;

@DataSource
public interface ConfigactivityMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Configactivity record);

	int insertSelective(Configactivity record);

	List<Configactivity> getList();

	int updateByPrimaryKeySelective(Configactivity record);

	int updateByPrimaryKey(Configactivity record);
}