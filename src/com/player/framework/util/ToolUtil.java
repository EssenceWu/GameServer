package com.player.framework.util;

public class ToolUtil {

	public static int getRandom(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}

}
