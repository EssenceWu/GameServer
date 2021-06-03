package com.player.game;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.net.MessageDispatcherFactory;
import com.player.framework.net.NettyFactory;
import com.player.framework.orm.OrmFactory;
import com.player.framework.redis.RedisFactory;
import com.player.framework.serializer.MessageFactory;
import com.player.framework.task.TaskScheduleFactory;
import com.player.game.config.ConfigFactory;

public class GameServer {

	private static Logger logger = LoggerFactory.getLogger(GameServer.class);

	public static void start() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		framework();
		stopWatch.stop();
		System.out.println("Loading all server[" + stopWatch.getTime() + "] successfully!");
	}

	private static void framework() {
		try {
			// 版本工厂
			ServerVersion.INSTANCE.initialize(ServerPath.VERSION_PATH);
			// 消息工厂
			MessageFactory.INSTANCE.initialize(ServerPath.MESSAGE_PATH);
			// 执行器工厂
			MessageDispatcherFactory.INSTANCE.initialize(ServerPath.CONTROLLER_PATH);
			// 数据库工厂
			OrmFactory.INSTANCE.initialize(ServerPath.ORM_PATH, ServerPath.DATABASE_PATH);
			// 缓存库工厂
			RedisFactory.INSTANCE.initialize(ServerConfig.getInstance().getRedisUrl());
			// 任务调度工厂
			TaskScheduleFactory.INSTANCE.initialize();
			// 策划配置工厂
			ConfigFactory.INSTANCE.initialize(ServerPath.CONFIG_PATH);
			// 通信调度工厂
			NettyFactory.INSTANCE.initialize(ServerConfig.getInstance().getServerPort());
			// 定时调度工厂
			// QuartzFactory.INSTANCE.initialize(ServerPath.QUARTZ_PATH);

		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
