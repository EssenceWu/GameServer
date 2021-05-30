package com.player.framework.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public enum QuartzFactory {

	INSTANCE;

	public void initialize(String fileName) throws Exception {
		try {
			System.out.println("Loading task crontab manager...");
			SchedulerFactory schedulerFactory = new StdSchedulerFactory(fileName);
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();
			System.out.println("Loading task crontab manager successfully!");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
