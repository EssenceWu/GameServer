package com.player.framework.net;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.annotation.message.Controller;
import com.player.framework.annotation.message.RequestMapping;
import com.player.framework.serializer.Message;
import com.player.framework.serializer.MessageFactory;
import com.player.framework.task.CmdExecutor;
import com.player.framework.task.MessageTask;
import com.player.framework.task.TaskScheduleFactory;
import com.player.framework.util.ClassScanner;

public enum MessageDispatcherFactory implements IoDispatcher {

	INSTANCE;

	private Map<Integer, CmdExecutor> container = new HashMap<>();

	private static Logger logger = LoggerFactory.getLogger(MessageDispatcherFactory.class);

	public void initialize(String packageName) throws Exception {
		try {
			System.out.println("Loading message executor...");
			Set<Class<?>> result = ClassScanner.getDeclaredAnnotationClass(packageName, Controller.class);
			for (Class<?> controller : result) {
				Object handler = controller.getDeclaredConstructor().newInstance();
				Method[] methods = controller.getDeclaredMethods();
				for (Method method : methods) {
					RequestMapping mapperAnnotation = method.getAnnotation(RequestMapping.class);
					if (mapperAnnotation != null) {
						short[] meta = MessageFactory.INSTANCE.getMessageMeta(method);
						if (meta == null) {
							throw new RuntimeException(
									String.format("Controller[%s] method[%s] lack of RequestMapping annotation",
											controller.getName(), method.getName()));
						}
						short module = meta[0];
						short cmd = meta[1];
						int key = MessageFactory.INSTANCE.key(module, cmd);
						CmdExecutor executer = this.container.get(key);
						if (executer != null) {
							throw new RuntimeException(String.format("Module[%d] cmd[%d] duplicated", module, cmd));
						}
						executer = CmdExecutor.valueOf(handler, method, method.getParameterTypes());
						this.container.put(key, executer);
					}
				}
			}
			System.out.println("Loading message executor[" + container.size() + "] successfully!");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void onSessionCreated(IdSession session) {
		try {
			session.setAttribute(PropertySession.UUID, PropertySession.uuid());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void dispatch(IdSession session, Message message) {
		try {
			short module = message.getModule();
			short cmd = message.getCmd();
			int uuid = (int) session.getAttribute(PropertySession.UUID);
			CmdExecutor executer = this.container.get(MessageFactory.INSTANCE.key(module, cmd));
			if (executer == null) {
				throw new Exception("Message executor missed, module=" + module + ",cmd=" + cmd);
			}
			Object controller = executer.getHandler();
			Object[] params = this.getParams(session, message, executer.getParams());
			TaskScheduleFactory.INSTANCE.addTask(MessageTask.valueOf(uuid, controller, executer.getMethod(), params));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void onSessionClosed(IdSession session) {
		try {
			PlayerSessionManager.INSTANCE.getPlayerId(session);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	private Object[] getParams(IdSession session, Message message, Class<?>[] params) {
		Object[] result = new Object[params == null ? 0 : params.length];
		for (int i = 0; i < result.length; i++) {
			Class<?> param = params[i];
			if (IdSession.class.isAssignableFrom(param)) {
				result[i] = session;
			} else if (Message.class.isAssignableFrom(param)) {
				result[i] = message;
			} else if (long.class.isAssignableFrom(param)) {
				result[i] = session.getPlayerId();
			} else if (Long.class.isAssignableFrom(param)) {
				result[i] = session.getPlayerId();
			}
		}
		return result;
	}

}
