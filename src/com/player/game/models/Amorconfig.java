package com.player.game.models;

public class Amorconfig {
	private Integer id;

	private String desic;

	private Integer def;

	private Integer mdef;

	private Integer addhp;

	private Integer addmp;

	private Integer buyprice;

	private Integer sellprice;

	public Amorconfig(Integer id, String desic, Integer def, Integer mdef, Integer addhp, Integer addmp,
			Integer buyprice, Integer sellprice) {
		this.id = id;
		this.desic = desic;
		this.def = def;
		this.mdef = mdef;
		this.addhp = addhp;
		this.addmp = addmp;
		this.buyprice = buyprice;
		this.sellprice = sellprice;
	}

	public Amorconfig() {
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

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}

	public Integer getMdef() {
		return mdef;
	}

	public void setMdef(Integer mdef) {
		this.mdef = mdef;
	}

	public Integer getAddhp() {
		return addhp;
	}

	public void setAddhp(Integer addhp) {
		this.addhp = addhp;
	}

	public Integer getAddmp() {
		return addmp;
	}

	public void setAddmp(Integer addmp) {
		this.addmp = addmp;
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