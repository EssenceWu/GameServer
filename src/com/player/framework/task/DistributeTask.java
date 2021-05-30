package com.player.framework.task;

public abstract class DistributeTask {

	private long beginTime;
	private long endTime;
	protected int distributeKey;

	public String getName() {
		return this.getClass().getSimpleName();
	}

	public long getBeginTime() {
		return this.beginTime;
	}

	public long getEndTime() {
		return this.endTime;
	}

	public void setBeginTime() {
		this.beginTime = System.currentTimeMillis();
	}

	public void setEndTime() {
		this.endTime = System.currentTimeMillis();
	}

	public int getDistributeKey() {
		return distributeKey;
	}

	abstract void action();

}
