package com.player.framework.task;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.net.MessageRouter;
import com.player.framework.serializer.Message;

public class MessageExecutor extends DistributeTask implements Executor {

	private Object handler;
	private Method method;
	private Object[] params;
	private long playerId;
	private Message message;

	private Logger logger = LoggerFactory.getLogger(MessageExecutor.class);

	public MessageExecutor(Object handler, Method method, Object[] params, int distributeKey) {
		this.handler = handler;
		this.method = method;
		this.params = params;
		this.distributeKey = distributeKey;
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

	public long getPlayerId() {
		return this.playerId;
	}

	public Message getMessage() {
		return message;
	}

	public void action() {
		try {
			Object response = this.method.invoke(handler, params);
			if (response != null) {
				MessageRouter.send(this.playerId, (Message) response);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static MessageExecutor valueOf(Object handler, Method method, Object[] params, int distributeKey) {
		return new MessageExecutor(handler, method, params, distributeKey);
	}

}
