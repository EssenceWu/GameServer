package com.player.framework.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.player.framework.thread.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum TaskScheduleFactory {

	INSTANCE;

	final private long MAX_EXEC_TIME = 30000L;
	final private long MONITOR_INTERVAL = 5000L;

	private AtomicBoolean run = new AtomicBoolean(true);
	private List<TaskWorker> workerPool = new ArrayList<>();
	private int CORE_SIZE = Runtime.getRuntime().availableProcessors();
	private ConcurrentMap<Thread, DistributeTask> currentTasks = new ConcurrentHashMap<>();

	private static Logger logger = LoggerFactory.getLogger(TaskScheduleFactory.class);

	public void initialize() {
		System.out.println("Loading task schedule manager...");
		for (int idx = 1; idx <= CORE_SIZE; idx++) {
			TaskWorker worker = new TaskWorker(idx);
			workerPool.add(worker);
			new NamedThreadFactory("task-schedule").newThread(worker).start();
		}
		new NamedThreadFactory("task-monitor").newThread(new TaskMoniter()).start();
		System.out.println("Loading task schedule manager successfully!");
	}

	public void addTask(DistributeTask task) {
		if (task == null) {
			throw new NullPointerException("Task is null");
		}
		int distributeKey = task.getDistributeKey() % workerPool.size();
		workerPool.get(distributeKey).add(task);
	}

	public void shutDown() {
		run.set(false);
	}

	protected class TaskWorker implements Runnable {

		protected int id;
		private BlockingQueue<DistributeTask> taskQueue = new LinkedBlockingQueue<>();

		TaskWorker(int index) {
			this.id = index;
		}

		public void add(DistributeTask task) {
			this.taskQueue.add(task);
		}

		public void run() {
			while (run.get()) {
				try {
					DistributeTask task = taskQueue.take();
					task.setBeginTime();
					Thread t = Thread.currentThread();
					currentTasks.put(t, task);
					task.action();
					currentTasks.remove(t);
					task.setEndTime();
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
					Thread.sleep(MONITOR_INTERVAL);
				} catch (InterruptedException e) {

				}
				for (DistributeTask task : currentTasks.values()) {
					if (task != null) {
						long currentTime = System.currentTimeMillis();
						if (currentTime - task.getBeginTime() > MAX_EXEC_TIME) {
							logger.error("Task [{}] execution timeout", task.getClass());
						}
					}
				}
			}
		}
	}

}
