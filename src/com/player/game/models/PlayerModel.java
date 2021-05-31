package com.player.game.models;

public class PlayerModel {
	public PlayerModel() {
		super();
	}

	public PlayerModel(long id, int uid, int level, String name, int job, int exp, String platform) {
		super();
		this.id = id;
		this.uid = uid;
		this.level = level;
		this.name = name;
		this.job = job;
		this.exp = exp;
		this.platform = platform;
	}

	private long id;
	private long uid;
	private int level;
	private String name;
	private int job;
	private int exp;
	private String platform;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "PlayerModel [id=" + id + ", uid=" + uid + ", level=" + level + ", name=" + name + ", job=" + job
				+ ", exp=" + exp + ", platform=" + platform + "]";
	}
}
