package com.player.framework.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum RedisFactory {

	INSTANCE;

	final int MAX_TOTAL = 100;
	final int MIN_IDLE = 1;
	final int MAX_IDLE = 10;

	private JedisPool jedisPool;

	public void initialize(String redisUrl) throws Exception {
		try {
			System.out.println("Loading redis service...");
			String[] hostPort = redisUrl.split(":");
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(this.MAX_TOTAL);
			poolConfig.setMinIdle(this.MIN_IDLE);
			poolConfig.setMaxIdle(this.MAX_TOTAL);
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
