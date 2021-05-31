package com.player.framework.orm;

import com.player.framework.net.IdSession;
import com.player.framework.net.PropertySession;
import com.player.framework.task.OrmTask;
import com.player.framework.task.TaskScheduleFactory;

public class OrmNotifyFactory {

	public static void add(IdSession session, Class<?> clazz, String methodName, Object param) {
		Object params[] = { clazz, methodName, param };
		OrmTask task = OrmTask.valueOf((int) session.getAttribute(PropertySession.UUID), params);
		TaskScheduleFactory.INSTANCE.addTask(task);
	}

}
