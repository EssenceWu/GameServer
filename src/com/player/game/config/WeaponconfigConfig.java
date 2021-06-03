package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.WeaponconfigMapper;
import com.player.game.models.Weaponconfig;

public class WeaponconfigConfig implements Config {

	private Map<Integer, Weaponconfig> config = new HashMap<Integer, Weaponconfig>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(WeaponconfigMapper.class);
		WeaponconfigMapper weaponconfigMapper = WeaponconfigMapper.class.cast(mapper.getObject());
		List<Weaponconfig> result = weaponconfigMapper.getList();
		for (Weaponconfig weaponconfig : result) {
			this.config.put(weaponconfig.getId(), weaponconfig);
		}
	}

	public Weaponconfig getConfig(int id) {
		return this.config.get(id);
	}
}
