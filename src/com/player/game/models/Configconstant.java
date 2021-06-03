package com.player.game.models;

public class Configconstant {
	private Integer id;

	private Integer intvalue;

	private String stringvalue;

	private String descrpition;

	public Configconstant(Integer id, Integer intvalue, String stringvalue, String descrpition) {
		this.id = id;
		this.intvalue = intvalue;
		this.stringvalue = stringvalue;
		this.descrpition = descrpition;
	}

	public Configconstant() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIntvalue() {
		return intvalue;
	}

	public void setIntvalue(Integer intvalue) {
		this.intvalue = intvalue;
	}

	public String getStringvalue() {
		return stringvalue;
	}

	public void setStringvalue(String stringvalue) {
		this.stringvalue = stringvalue;
	}

	public String getDescrpition() {
		return descrpition;
	}

	public void setDescrpition(String descrpition) {
		this.descrpition = descrpition;
	}
}