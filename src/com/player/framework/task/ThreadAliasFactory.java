package com.player.framework.task;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadAliasFactory implements ThreadFactory {

	private ThreadGroup threadGroup;
	private String groupName;
	private boolean daemo;
	private AtomicInteger atomicInteger = new AtomicInteger(1);

	public ThreadAliasFactory(String group) {
		this(group, false);
	}

	public ThreadAliasFactory(String group, boolean daemo) {
		this.groupName = group;
		this.daemo = daemo;
	}

	private String getAlias() {
		return this.groupName + "-thread-" + this.atomicInteger.getAndIncrement();
	}

	public Thread newThread(Runnable runnable) {
		String alias = getAlias();
		Thread thread = new Thread(threadGroup, runnable, alias, 0);
		thread.setDaemon(daemo);
		return thread;
	}

}
