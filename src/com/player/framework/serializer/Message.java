package com.player.framework.serializer;

import com.player.framework.annotation.message.MessageMeta;

public class Message {

	public short getModule() {
		MessageMeta annotation = getClass().getAnnotation(MessageMeta.class);
		if (annotation != null) {
			return annotation.module();
		}
		return 0;
	}

	public short getCmd() {
		MessageMeta annotation = getClass().getAnnotation(MessageMeta.class);
		if (annotation != null) {
			return annotation.cmd();
		}
		return 0;
	}

}
