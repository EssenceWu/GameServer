package com.player.game;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.net.MessageDispatcherFactory;
import com.player.framework.net.NettyFactory;
import com.player.framework.orm.OrmFactory;
import com.player.framework.quartz.QuartzFactory;
import com.player.framework.redis.RedisFactory;
import com.player.framework.serializer.MessageFactory;
import com.player.framework.task.TaskScheduleFactory;
import com.player.framework.util.XmlWrapper;

public class GameServer {

	private static Logger logger = LoggerFactory.getLogger(GameServer.class);

	public static void start() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		framework();
		stopWatch.stop();
	}

	private static void framework() {
		try {
			// 加载配置
			ServerConfig serverConfig = XmlWrapper.load(ServerPath.SERVER_PATH, ServerConfig.class);
			// 版本工厂
			ServerVersion.INSTANCE.initialize(ServerPath.VERSION_PATH);
			// 协议工厂
			MessageFactory.INSTANCE.initialize(ServerPath.MESSAGE_PATH);
			// 事件工厂
			MessageDispatcherFactory.INSTANCE.initialize(ServerPath.CONTROLLER_PATH);
			// 数据库工厂
			OrmFactory.INSTANCE.initialize(ServerPath.ORM_PATH);
			// 缓存库工厂
			RedisFactory.INSTANCE.initialize(serverConfig.getRedisUrl());
			// 任务调度工厂
			TaskScheduleFactory.INSTANCE.initialize();
			// 定时调度工厂
			QuartzFactory.INSTANCE.initialize(ServerPath.QUARTZ_PATH);
			// 通信调度工厂
			NettyFactory.INSTANCE.initialize(serverConfig.getServerPort());

		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
