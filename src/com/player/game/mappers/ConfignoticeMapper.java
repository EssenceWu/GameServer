package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.DataSource;
import com.player.game.models.Confignotice;

@DataSource
public interface ConfignoticeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Confignotice record);

	int insertSelective(Confignotice record);

	List<Confignotice> getList();

	int updateByPrimaryKeySelective(Confignotice record);

	int updateByPrimaryKey(Confignotice record);
}