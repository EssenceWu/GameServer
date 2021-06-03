package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.ConfigactivityMapper;
import com.player.game.models.Configactivity;

public class ConfigactivityConfig implements Config {

	private Map<Integer, Configactivity> config = new HashMap<Integer, Configactivity>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(ConfigactivityMapper.class);
		ConfigactivityMapper amorconfigMapper = ConfigactivityMapper.class.cast(mapper.getObject());
		List<Configactivity> result = amorconfigMapper.getList();
		for (Configactivity configactivity : result) {
			this.config.put(configactivity.getId(), configactivity);
		}
	}

	public Configactivity getConfig(int id) {
		return this.config.get(id);
	}
}
