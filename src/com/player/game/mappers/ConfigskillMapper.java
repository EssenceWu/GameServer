package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Configskill;

@DataSource
public interface ConfigskillMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Configskill record);

	int insertSelective(Configskill record);

	List<Configskill> getList();

	int updateByPrimaryKeySelective(Configskill record);

	int updateByPrimaryKey(Configskill record);
}