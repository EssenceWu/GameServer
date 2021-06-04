package com.player.game.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.cache.CacheAdapter;
import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.RucksackitemMapper;
import com.player.game.models.Rucksackitem;

public class RucksackitemByPlayerIdCache extends CacheAdapter<Long, Map<Integer, Rucksackitem>> {

	public static RucksackitemByPlayerIdCache rucksackitemByPlayerIdCache = new RucksackitemByPlayerIdCache();

	public static Map<Integer, Rucksackitem> get(long playerId) {
		return rucksackitemByPlayerIdCache.getCache(playerId);
	}

	public Map<Integer, Rucksackitem> loadCache(Long playerId) {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(RucksackitemMapper.class);
		RucksackitemMapper rucksackitemMapper = RucksackitemMapper.class.cast(mapper.getObject());
		List<Rucksackitem> list = rucksackitemMapper.getList(playerId);
		Map<Integer, Rucksackitem> result = new HashMap<Integer, Rucksackitem>();
		for (Rucksackitem rucksackitem : list) {
			result.put(rucksackitem.getItemid(), rucksackitem);
		}
		mapper.close();
		return result;
	}

}
