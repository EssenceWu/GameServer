package com.player.framework.task;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.orm.Mapper;
import com.player.framework.orm.OrmFactory;

public class OrmTask extends TaskAdapter implements Executor {

	private Object handler;
	private Method method;
	private Object params[];

	private Logger logger = LoggerFactory.getLogger(OrmTask.class);

	public static OrmTask valueOf(int uuid, Object params[]) {
		return new OrmTask(uuid, params);
	}

	public OrmTask(int uuid, Object params[]) {
		this.uuid = uuid;
		this.params = params;
	}

	public Object getHandler() {
		return this.handler;
	}

	public Method getMethod() {
		return this.method;
	}

	public Object getParams() {
		return this.params;
	}

	public void action() {
		try {
			Class<?> clazz = (Class<?>) this.params[0];
			Mapper mapper = OrmFactory.INSTANCE.getMapper(clazz);
			this.method = clazz.getMethod(String.valueOf(params[1]), this.params[2].getClass());
			this.method.invoke(mapper.getObject(), this.params[2]);
			mapper.commit();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
