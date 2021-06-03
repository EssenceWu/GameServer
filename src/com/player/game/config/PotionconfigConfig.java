package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.PotionconfigMapper;
import com.player.game.models.Potionconfig;

public class PotionconfigConfig implements Config {

	private Map<Integer, Potionconfig> config = new HashMap<Integer, Potionconfig>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(PotionconfigMapper.class);
		PotionconfigMapper potionconfigMapper = PotionconfigMapper.class.cast(mapper.getObject());
		List<Potionconfig> result = potionconfigMapper.getList();
		for (Potionconfig potionconfig : result) {
			this.config.put(potionconfig.getId(), potionconfig);
		}
	}

	public Potionconfig getConfig(int id) {
		return this.config.get(id);
	}

}