package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.ConfigconstantMapper;
import com.player.game.models.Configconstant;

public class ConfigconstantConfig implements Config {

	private Map<Integer, Configconstant> config = new HashMap<Integer, Configconstant>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(ConfigconstantMapper.class);
		ConfigconstantMapper amorconfigMapper = ConfigconstantMapper.class.cast(mapper.getObject());
		List<Configconstant> result = amorconfigMapper.getList();
		for (Configconstant configconstant : result) {
			this.config.put(configconstant.getId(), configconstant);
		}
	}

	public Configconstant getConfig(int id) {
		return this.config.get(id);
	}
}
