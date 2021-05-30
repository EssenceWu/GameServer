package com.player.game;

import java.util.Properties;

import org.apache.ibatis.io.Resources;

public enum ServerVersion {

	INSTANCE;

	public void initialize(String fileName) throws Exception {
		try {
			System.out.println("Loading version...");
			Properties properties = Resources.getResourceAsProperties(fileName);
			String version = properties.getProperty("server.version");
			System.out.println("Loading version[" + version + "] successfully!");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
