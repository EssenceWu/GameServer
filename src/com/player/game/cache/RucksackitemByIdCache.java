package com.player.game.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.cache.CacheAdapter;
import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.RucksackitemMapper;
import com.player.game.models.Rucksackitem;

public class RucksackitemByIdCache extends CacheAdapter<Long, Map<Integer, Rucksackitem>> {

	public static RucksackitemByIdCache rucksackitemByIdCache = new RucksackitemByIdCache();

	public static Map<Integer, Rucksackitem> get(long playerId) {
		return rucksackitemByIdCache.getCache(playerId);
	}

	public static Rucksackitem getItem(long playerId, int itemId) {
		Map<Integer, Rucksackitem> itemMap = RucksackitemByIdCache.get(playerId);
		if (itemMap == null) {
			return null;
		} else {
			return itemMap.get(itemId);
		}
	}

	public Map<Integer, Rucksackitem> loadCache(Long playerId) {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(RucksackitemMapper.class);
		RucksackitemMapper rucksackitemMapper = RucksackitemMapper.class.cast(mapper.getObject());
		List<Rucksackitem> result = rucksackitemMapper.getList(playerId);
		Map<Integer, Rucksackitem> itemMap = new HashMap<Integer, Rucksackitem>();
		for (Rucksackitem rucksackitem : result) {
			itemMap.put(rucksackitem.getItemid(), rucksackitem);
		}
		mapper.close();
		return itemMap;
	}

}
