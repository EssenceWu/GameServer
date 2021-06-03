package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.ConfigskillMapper;
import com.player.game.models.Configskill;

public class ConfigskillConfig implements Config {

	private Map<Integer, Configskill> config = new HashMap<Integer, Configskill>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(ConfigskillMapper.class);
		ConfigskillMapper configskillMapper = ConfigskillMapper.class.cast(mapper.getObject());
		List<Configskill> result = configskillMapper.getList();
		for (Configskill amorconfig : result) {
			this.config.put(amorconfig.getId(), amorconfig);
		}
	}

	public Configskill getConfig(int id) {
		return this.config.get(id);
	}

}
