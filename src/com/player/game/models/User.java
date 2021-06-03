package com.player.game.models;

public class User {

	public User() {
		super();
	}

	public User(long id, String uname, String upwd, String unick, String guestKey, int isGuest, int status) {
		super();
		this.id = id;
		this.uname = uname;
		this.upwd = upwd;
		this.unick = unick;
		this.guestKey = guestKey;
		this.isGuest = isGuest;
		this.status = status;
	}

	private long id;
	private String uname;
	private String upwd;
	private String unick;
	private String guestKey;
	private int isGuest;
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUnick() {
		return unick;
	}

	public void setUnick(String unick) {
		this.unick = unick;
	}

	public String getGuestKey() {
		return guestKey;
	}

	public void setGuestKey(String guestKey) {
		this.guestKey = guestKey;
	}

	public int getIsGuest() {
		return isGuest;
	}

	public void setIsGuest(int isGuest) {
		this.isGuest = isGuest;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", uname=" + uname + ", upwd=" + upwd + ", unick=" + unick + ", guestKey="
				+ guestKey + ", isGuest=" + isGuest + ", status=" + status + "]";
	}
}
