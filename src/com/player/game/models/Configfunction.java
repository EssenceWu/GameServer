package com.player.game.models;

public class Configfunction {
	private Integer id;

	private String name;

	private String opentype;

	private Integer opentarget;

	public Configfunction(Integer id, String name, String opentype, Integer opentarget) {
		this.id = id;
		this.name = name;
		this.opentype = opentype;
		this.opentarget = opentarget;
	}

	public Configfunction() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpentype() {
		return opentype;
	}

	public void setOpentype(String opentype) {
		this.opentype = opentype;
	}

	public Integer getOpentarget() {
		return opentarget;
	}

	public void setOpentarget(Integer opentarget) {
		this.opentarget = opentarget;
	}
}