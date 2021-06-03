package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.ConfignoticeMapper;
import com.player.game.models.Confignotice;

public class ConfignoticeConfig implements Config {

	private Map<Integer, Confignotice> config = new HashMap<Integer, Confignotice>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(ConfignoticeMapper.class);
		ConfignoticeMapper confignoticeMapper = ConfignoticeMapper.class.cast(mapper.getObject());
		List<Confignotice> result = confignoticeMapper.getList();
		for (Confignotice confignotice : result) {
			this.config.put(confignotice.getId(), confignotice);
		}
	}

	public Confignotice getConfig(int id) {
		return this.config.get(id);
	}

}
