package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.MonestconfigMapper;
import com.player.game.models.Monestconfig;

public class MonestconfigConfig implements Config {

	private Map<Integer, Monestconfig> config = new HashMap<Integer, Monestconfig>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(MonestconfigMapper.class);
		MonestconfigMapper monestconfigMapper = MonestconfigMapper.class.cast(mapper.getObject());
		List<Monestconfig> result = monestconfigMapper.getList();
		for (Monestconfig monestconfig : result) {
			this.config.put(monestconfig.getId(), monestconfig);
		}
	}

	public Monestconfig getConfig(int id) {
		return this.config.get(id);
	}

}