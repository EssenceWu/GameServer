package com.player.framework.orm;

import com.player.framework.task.OrmTask;
import com.player.framework.task.TaskScheduleFactory;

public class OrmAsyncFactory {

	public static void notify(int uuid, Class<?> clazz, String methodName, Object param) {
		Object params[] = { clazz, methodName, param };
		OrmTask task = OrmTask.valueOf(uuid, params);
		TaskScheduleFactory.INSTANCE.addTask(task);
	}

}
