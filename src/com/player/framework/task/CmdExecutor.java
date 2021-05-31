package com.player.framework.task;

import java.lang.reflect.Method;

public class CmdExecutor implements Executor {

	private Object handler;
	private Method method;
	private Class<?>[] params;

	public static CmdExecutor valueOf(Object handler, Method method, Class<?>[] params) {
		return new CmdExecutor(handler, method, params);
	}

	public CmdExecutor(Object handler, Method method, Class<?>[] params) {
		this.handler = handler;
		this.method = method;
		this.params = params;
	}

	public Object getHandler() {
		return this.handler;
	}

	public Method getMethod() {
		return this.method;
	}

	public Class<?>[] getParams() {
		return this.params;
	}

}
