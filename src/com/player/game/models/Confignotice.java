package com.player.game.models;

public class Confignotice {
	private Integer id;

	private String module;

	private Short channel;

	private String content;

	public Confignotice(Integer id, String module, Short channel, String content) {
		this.id = id;
		this.module = module;
		this.channel = channel;
		this.content = content;
	}

	public Confignotice() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Short getChannel() {
		return channel;
	}

	public void setChannel(Short channel) {
		this.channel = channel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}