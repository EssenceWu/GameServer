package com.player.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;
import com.player.game.mappers.PlayerlevelconfigMapper;
import com.player.game.models.Playerlevelconfig;

public class PlayerlevelconfigConfig implements Config {

	private Map<Integer, Playerlevelconfig> config = new HashMap<Integer, Playerlevelconfig>();

	public void loadConfig() {
		Mapper mapper = OrmFactory.INSTANCE.getMapper(PlayerlevelconfigMapper.class);
		PlayerlevelconfigMapper playerlevelconfigMapper = PlayerlevelconfigMapper.class.cast(mapper.getObject());
		List<Playerlevelconfig> result = playerlevelconfigMapper.getList();
		for (Playerlevelconfig playerlevelconfig : result) {
			this.config.put(playerlevelconfig.getLevel(), playerlevelconfig);
		}
	}

	public Playerlevelconfig getConfig(int id) {
		return this.config.get(id);
	}

}
