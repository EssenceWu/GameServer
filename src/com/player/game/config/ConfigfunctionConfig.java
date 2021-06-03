package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.ConfigfunctionMapper;
import com.player.game.models.Configfunction;

public class ConfigfunctionConfig implements Config {

	private Map<Integer, Configfunction> config = new HashMap<Integer, Configfunction>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(ConfigfunctionMapper.class);
		ConfigfunctionMapper configfunctionMapper = ConfigfunctionMapper.class.cast(mapper.getObject());
		List<Configfunction> result = configfunctionMapper.getList();
		for (Configfunction configfunction : result) {
			this.config.put(configfunction.getId(), configfunction);
		}
	}

	public Configfunction getConfig(int id) {
		return this.config.get(id);
	}

}
