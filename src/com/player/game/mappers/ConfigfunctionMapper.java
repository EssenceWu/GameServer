package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Configfunction;

@DataSource
public interface ConfigfunctionMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Configfunction record);

	int insertSelective(Configfunction record);

	List<Configfunction> getList();

	int updateByPrimaryKeySelective(Configfunction record);

	int updateByPrimaryKey(Configfunction record);
}