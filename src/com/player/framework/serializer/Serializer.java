package com.player.framework.serializer;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

public class Serializer {

	@SuppressWarnings("unchecked")
	public static byte[] encode(Message message) throws Exception {
		try {
			Class<Message> clazz = (Class<Message>) message.getClass();
			Codec<Message> codec = ProtobufProxy.create(clazz);
			return codec.encode(message);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static byte[] encodeObject(Object object) throws Exception {
		try {
			Class<Object> clazz = (Class<Object>) object.getClass();
			Codec<Object> codec = ProtobufProxy.create(clazz);
			return codec.encode(object);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static Message decode(short module, short cmd, byte[] body) throws Exception {
		try {
			Class<?> clazz = MessageFactory.INSTANCE.getMessage(module, cmd);
			if (clazz == null) {
				return null;
			}
			if (body == null) {
				return Message.class.cast(clazz.getDeclaredConstructor().newInstance());
			}
			Codec<?> codec = ProtobufProxy.create(clazz);
			return (Message) codec.decode(body);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static Object decodeObject(Class<?> clazz, byte[] body) throws Exception {
		try {
			if (body == null) {
				return null;
			}
			Codec<?> codec = ProtobufProxy.create(clazz);
			return (Object) codec.decode(body);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
