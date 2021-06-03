package com.player.game.models;

public class Potionconfig {
	private Integer id;

	private String desic;

	private Integer hp;

	private Integer mp;

	private Integer buyprice;

	private Integer sellprice;

	public Potionconfig(Integer id, String desic, Integer hp, Integer mp, Integer buyprice, Integer sellprice) {
		this.id = id;
		this.desic = desic;
		this.hp = hp;
		this.mp = mp;
		this.buyprice = buyprice;
		this.sellprice = sellprice;
	}

	public Potionconfig() {
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