package com.player.framework.util;

import org.apache.ibatis.io.Resources;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlWrapper {

	private static Logger logger = LoggerFactory.getLogger(XmlWrapper.class);

	public static <T> T load(String fileName, Class<T> configClass) {
		T object = null;
		try {
			Serializer serializer = new Persister();
			object = serializer.read(configClass, Resources.getResourceAsReader(fileName));
		} catch (Exception e) {
			logger.error("", e);
		}
		return object;
	}

}
