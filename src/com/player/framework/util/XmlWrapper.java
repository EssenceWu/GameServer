package com.player.framework.util;

import org.apache.ibatis.io.Resources;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XmlWrapper {

	public static <T> T load(String fileName, Class<T> configClass) throws Exception {
		try {
			Serializer serializer = new Persister();
			return serializer.read(configClass, Resources.getResourceAsReader(fileName));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
