package com.player.framework.task;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.net.MessageRouter;
import com.player.framework.serializer.Message;

public class MessageTask extends TaskAdapter implements Executor {

	private Object handler;
	private Method method;
	private Object[] params;
	private long playerId;

	private Logger logger = LoggerFactory.getLogger(MessageTask.class);

	public static MessageTask valueOf(int uuid, Object handler, Method method, Object[] params) {
		return new MessageTask(uuid, handler, method, params);
	}

	public MessageTask(int uuid, Object handler, Method method, Object[] params) {
		this.uuid = uuid;
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

	public Object[] getParams() {
		return this.params;
	}

	public void action() {
		try {
			Object response = this.method.invoke(this.handler, this.params);
			if (response != null) {
				MessageRouter.send(this.playerId, (Message) response);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
