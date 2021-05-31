package com.player.framework.task;

public abstract class TaskAdapter {

	private long time;
	protected int uuid;

	public long getTime() {
		return this.time;
	}

	public void setTime() {
		this.time = System.currentTimeMillis();
	}

	public int uuid() {
		return this.uuid;
	}

	abstract void action();

}
