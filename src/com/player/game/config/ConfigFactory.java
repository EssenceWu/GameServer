package com.player.game.config;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.util.ClassScanner;

public enum ConfigFactory {

	INSTANCE;

	private ConcurrentMap<Class<?>, Config> container = new ConcurrentHashMap<>();

	private static Logger logger = LoggerFactory.getLogger(ConfigFactory.class);

	public void initialize(String path) throws Exception {
		try {
			System.out.println("Loading game config...");
			Set<Class<?>> result = ClassScanner.getDeclaredSubClass(path, Config.class);
			result.forEach(clazz -> {
				try {
					Config config = Config.class.cast(clazz.getDeclaredConstructor().newInstance());
					config.loadConfig();
					this.container.put(clazz, config);
				} catch (Exception e) {
					logger.error("", e);
				}
			});
			System.out.println("Loading game config[" + this.container.size() + "] successfully!");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Config getConfig(Class<?> clazz) {
		return this.container.get(clazz);
	}

}
