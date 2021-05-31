package com.player.framework.orm;

import com.player.framework.task.OrmTask;
import com.player.framework.task.TaskScheduleFactory;

public class OrmNotifyFactory {

	public static void add(int uuid, Class<?> clazz, String methodName, Object param) {
		Object params[] = { clazz, methodName, param };
		OrmTask task = OrmTask.valueOf(uuid, params);
		TaskScheduleFactory.INSTANCE.addTask(task);
	}

}
