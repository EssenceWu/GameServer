package com.player.framework.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum TaskScheduleFactory {

	INSTANCE;

	final private long max_exec_time = 30000L;
	final private long monitor_sleep_time = 5000L;

	private AtomicBoolean run = new AtomicBoolean(true);
	private List<TaskWorker> workerPool = new ArrayList<>();
	private int core_size = Runtime.getRuntime().availableProcessors();
	private ConcurrentMap<Thread, TaskAdapter> currentTasks = new ConcurrentHashMap<>();

	private static Logger logger = LoggerFactory.getLogger(TaskScheduleFactory.class);

	public void initialize() {
		System.out.println("Loading task schedule manager...");
		for (int i = 1; i <= core_size; i++) {
			TaskWorker worker = new TaskWorker(i);
			workerPool.add(worker);
			new ThreadAliasFactory("task-schedule").newThread(worker).start();
		}
		new ThreadAliasFactory("task-monitor").newThread(new TaskMoniter()).start();
		System.out.println("Loading task schedule manager successfully!");
	}

	public void addTask(TaskAdapter task) {
		if (task == null) {
			throw new NullPointerException("Task is null");
		}
		int distributeKey = task.uuid() % workerPool.size();
		workerPool.get(distributeKey).add(task);
	}

	public void shutDown() {
		run.set(false);
	}

	protected class TaskWorker implements Runnable {

		protected int id;
		private BlockingQueue<TaskAdapter> taskQueue = new LinkedBlockingQueue<>();

		TaskWorker(int index) {
			this.id = index;
		}

		public void add(TaskAdapter task) {
			this.taskQueue.add(task);
		}

		public void run() {
			while (run.get()) {
				try {
					TaskAdapter task = taskQueue.take();
					task.setTime();
					Thread t = Thread.currentThread();
					currentTasks.put(t, task);
					task.action();
					currentTasks.remove(t);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}

	}

	private class TaskMoniter implements Runnable {
		public void run() {
			for (;;) {
				try {
					Thread.sleep(monitor_sleep_time);
				} catch (InterruptedException e) {

				}
				for (TaskAdapter task : currentTasks.values()) {
					if (task != null) {
						long currentTime = System.currentTimeMillis();
						if (currentTime - task.getTime() > max_exec_time) {
							logger.error("Task [{}] execution timeout", task.getClass());
						}
					}
				}
			}
		}
	}

}
