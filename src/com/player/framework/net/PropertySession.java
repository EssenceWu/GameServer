package com.player.framework.net;

import java.util.concurrent.atomic.AtomicInteger;

public interface PropertySession {

	public static String UID = "UID";
	public static String UUID = "UUID";
	public static String PLAYER_ID = "PLAYER_ID";
	public static String SESSION_ID = "SESSION_ID";
	public static AtomicInteger atomicInteger = new AtomicInteger();

	public static int uuid() {
		return atomicInteger.getAndIncrement();
	}

}
