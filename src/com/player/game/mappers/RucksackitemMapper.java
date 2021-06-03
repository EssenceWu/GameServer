package com.player.game.mappers;

import java.util.List;

import com.player.framework.annotation.database.UserSource;
import com.player.game.models.Rucksackitem;

@UserSource
public interface RucksackitemMapper {
	int deleteByPrimaryKey(Integer itemkey);

	int insert(Rucksackitem record);

	int insertSelective(Rucksackitem record);

	List<Rucksackitem> getList(Long id);

	int updateByPrimaryKeySelective(Rucksackitem record);

	int updateByPrimaryKey(Rucksackitem record);
}