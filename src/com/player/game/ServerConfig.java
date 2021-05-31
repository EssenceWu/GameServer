package com.player.game;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.player.framework.util.XmlWrapper;

@Root(name = "server")
public class ServerConfig {

	private static volatile ServerConfig serverConfig;
	private static Logger logger = LoggerFactory.getLogger(ServerConfig.class);

	public static ServerConfig getInstance() {
		if (serverConfig != null) {
			return serverConfig;
		}
		synchronized (ServerConfig.class) {
			if (serverConfig == null) {
				try {
					serverConfig = XmlWrapper.load(ServerPath.SERVER_PATH, ServerConfig.class);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
		return serverConfig;
	}

	/** ������id */
	@Element(required = true)
	private int serverId;

	/** �������˿� */
	@Element(required = true)
	private int serverPort;

	/** ��̨����˿� */
	@Element(required = true)
	private int adminPort;

	/** ��̨������ģʽ */
	@Element(required = true)
	private String whiteIps;

	/** ƥ���http��ַ */
	@Element(required = true)
	private String matchUrl;

	/** �����Ƿ���� */
	@Element(required = true)
	private boolean fight;

	/** �������˿� */
	@Element(required = true)
	private int crossPort;

	/** redis server url {http:port} */
	@Element(required = true)
	private String redisUrl;

	public int getServerId() {
		return this.serverId;
	}

	public int getServerPort() {
		return this.serverPort;
	}

	public int getHttpPort() {
		return adminPort;
	}

	public String getRedisUrl() {
		return redisUrl;
	}

}
