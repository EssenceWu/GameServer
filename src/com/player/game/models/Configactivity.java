package com.player.game.models;

public class Configactivity {
	private Integer id;

	private Integer type;

	private String name;

	public Configactivity(Integer id, Integer type, String name) {
		this.id = id;
		this.type = type;
		this.name = name;
	}

	public Configactivity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}