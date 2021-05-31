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
		System.out.println("Loading all server[" + stopWatch.getTime() + "] successfully!");
	}

	private static void framework() {
		try {
			// ���ط�������
			ServerConfig serverConfig = XmlWrapper.load(ServerPath.SERVER_PATH, ServerConfig.class);
			// ����汾����
			ServerVersion.INSTANCE.initialize(ServerPath.VERSION_PATH);
			// ��ϢЭ�鹤��
			MessageFactory.INSTANCE.initialize(ServerPath.MESSAGE_PATH);
			// ��Ϣִ��������
			MessageDispatcherFactory.INSTANCE.initialize(ServerPath.CONTROLLER_PATH);
			// ���ݿ⹤��
			OrmFactory.INSTANCE.initialize(ServerPath.ORM_PATH, ServerPath.DATABASE_PATH);
			// ����⹤��
			RedisFactory.INSTANCE.initialize(serverConfig.getRedisUrl());
			// ������ȹ���
			TaskScheduleFactory.INSTANCE.initialize();
			// ��ʱ���ȹ���
			QuartzFactory.INSTANCE.initialize(ServerPath.QUARTZ_PATH);
			// ͨ�ŵ��ȹ���
			NettyFactory.INSTANCE.initialize(serverConfig.getServerPort());

		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
