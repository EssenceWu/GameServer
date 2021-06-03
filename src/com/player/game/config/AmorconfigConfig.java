package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.AmorconfigMapper;
import com.player.game.models.Amorconfig;

public class AmorconfigConfig implements Config {

	private Map<Integer, Amorconfig> config = new HashMap<Integer, Amorconfig>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(AmorconfigMapper.class);
		AmorconfigMapper amorconfigMapper = AmorconfigMapper.class.cast(mapper.getObject());
		List<Amorconfig> result = amorconfigMapper.getList();
		for (Amorconfig amorconfig : result) {
			this.config.put(amorconfig.getId(), amorconfig);
		}
	}

	public Amorconfig getConfig(int id) {
		return this.config.get(id);
	}

}
