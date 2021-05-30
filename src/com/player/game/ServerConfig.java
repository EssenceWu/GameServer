package com.player.game;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "server")
public class ServerConfig {

	/** 服务器id */
	@Element(required = true)
	private int serverId;

	/** 服务器端口 */
	@Element(required = true)
	private int serverPort;

	/** 后台管理端口 */
	@Element(required = true)
	private int adminPort;

	/** 后台白名单模式 */
	@Element(required = true)
	private String whiteIps;

	/** 匹配服http地址 */
	@Element(required = true)
	private String matchUrl;

	/** 本服是否為跨服 */
	@Element(required = true)
	private boolean fight;

	/** 对外跨服端口 */
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
