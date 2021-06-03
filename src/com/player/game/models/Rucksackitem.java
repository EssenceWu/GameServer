package com.player.game.models;

public class Rucksackitem {
	private Integer itemkey;

	private Long id;

	private Integer itemid;

	private Integer count;

	public Rucksackitem(Integer itemkey, Long id, Integer itemid, Integer count) {
		this.itemkey = itemkey;
		this.id = id;
		this.itemid = itemid;
		this.count = count;
	}

	public Rucksackitem() {
		super();
	}

	public Integer getItemkey() {
		return itemkey;
	}

	public void setItemkey(Integer itemkey) {
		this.itemkey = itemkey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Rucksackitem [itemkey=" + itemkey + ", id=" + id + ", itemid=" + itemid + ", count=" + count + "]";
	}
}