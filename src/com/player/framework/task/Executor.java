package com.player.framework.task;

import java.lang.reflect.Method;

interface Executor {

	public Object getHandler();

	public Method getMethod();

	public Object getParams();

}
