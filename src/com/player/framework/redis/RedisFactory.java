package com.player.framework.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum RedisFactory {

	INSTANCE;

	final int max_tatol = 100;
	final int min_idle = 1;
	final int max_idle = 10;

	private JedisPool jedisPool;

	public void initialize(String redisUrl) throws Exception {
		try {
			System.out.println("Loading redis service...");
			String[] hostPort = redisUrl.split(":");
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(this.max_tatol);
			poolConfig.setMinIdle(this.min_idle);
			poolConfig.setMaxIdle(this.max_idle);
			this.jedisPool = new JedisPool(poolConfig, hostPort[0], Integer.parseInt(hostPort[1]));
			System.out.println("Loading redis service successfully!");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Jedis getResource() {
		return this.jedisPool.getResource();
	}

}
