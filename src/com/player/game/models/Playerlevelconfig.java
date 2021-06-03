package com.player.game.models;

public class Playerlevelconfig {
	private Integer level;

	private Integer needexp;

	private Integer hp;

	private Integer mp;

	private Integer attack;

	private Integer def;

	public Playerlevelconfig(Integer level, Integer needexp, Integer hp, Integer mp, Integer attack, Integer def) {
		this.level = level;
		this.needexp = needexp;
		this.hp = hp;
		this.mp = mp;
		this.attack = attack;
		this.def = def;
	}

	public Playerlevelconfig() {
		super();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getNeedexp() {
		return needexp;
	}

	public void setNeedexp(Integer needexp) {
		this.needexp = needexp;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getMp() {
		return mp;
	}

	public void setMp(Integer mp) {
		this.mp = mp;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}
}