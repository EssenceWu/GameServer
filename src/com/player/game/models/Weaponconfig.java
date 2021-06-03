package com.player.game.models;

public class Weaponconfig {
	private Integer id;

	private String desic;

	private Integer attack;

	private Integer def;

	private Integer buyprice;

	private Integer sellprice;

	public Weaponconfig(Integer id, String desic, Integer attack, Integer def, Integer buyprice, Integer sellprice) {
		this.id = id;
		this.desic = desic;
		this.attack = attack;
		this.def = def;
		this.buyprice = buyprice;
		this.sellprice = sellprice;
	}

	public Weaponconfig() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesic() {
		return desic;
	}

	public void setDesic(String desic) {
		this.desic = desic;
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

	public Integer getBuyprice() {
		return buyprice;
	}

	public void setBuyprice(Integer buyprice) {
		this.buyprice = buyprice;
	}

	public Integer getSellprice() {
		return sellprice;
	}

	public void setSellprice(Integer sellprice) {
		this.sellprice = sellprice;
	}
}