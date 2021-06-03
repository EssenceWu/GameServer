package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Configconstant;

@DataSource
public interface ConfigconstantMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Configconstant record);

	int insertSelective(Configconstant record);

	List<Configconstant> getList();

	int updateByPrimaryKeySelective(Configconstant record);

	int updateByPrimaryKey(Configconstant record);
}