package com.player.framework.task;

import java.util.concurrent.atomic.AtomicInteger;

final public class Distribute {

	final public static String DISTRIBUTE_KEY = "DISTRIBUTE_KEY";

	private static AtomicInteger atomicInteger = new AtomicInteger();

	public static int distributeKey() {
		return atomicInteger.getAndIncrement();
	}

}
