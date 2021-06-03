package com.player.game.models;

public class Configskill {
	private Integer id;

	private Integer skillid;

	private Integer bufftime;

	private String name;

	private String effect;

	public Configskill(Integer id, Integer skillid, Integer bufftime, String name, String effect) {
		this.id = id;
		this.skillid = skillid;
		this.bufftime = bufftime;
		this.name = name;
		this.effect = effect;
	}

	public Configskill() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSkillid() {
		return skillid;
	}

	public void setSkillid(Integer skillid) {
		this.skillid = skillid;
	}

	public Integer getBufftime() {
		return bufftime;
	}

	public void setBufftime(Integer bufftime) {
		this.bufftime = bufftime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}
}