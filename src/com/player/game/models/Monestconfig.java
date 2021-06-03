package com.player.game.models;

public class Monestconfig {
	private Integer id;

	private String name;

	private Integer speed;

	private Integer attack;

	private Integer def;

	private Integer hp;

	private Integer exp;

	private String desic;

	public Monestconfig(Integer id, String name, Integer speed, Integer attack, Integer def, Integer hp, Integer exp,
			String desic) {
		this.id = id;
		this.name = name;
		this.speed = speed;
		this.attack = attack;
		this.def = def;
		this.hp = hp;
		this.exp = exp;
		this.desic = desic;
	}

	public Monestconfig() {
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

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
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

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public String getDesic() {
		return desic;
	}

	public void setDesic(String desic) {
		this.desic = desic;
	}
}