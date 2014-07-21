package impl;

import java.util.ArrayList;
import java.util.List;

import Util.STATUS;

import interfaces.Executor;
import interfaces.TaskQueue;
import interfaces.ThreadPool;

public class ThreadPoolImpl implements ThreadPool {

	private TaskQueue taskQueue;
	private STATUS status = STATUS.NEW;
	private final int COUNT = 5;
	private List<Executor> pool = new ArrayList<Executor>();

	public ThreadPoolImpl(TaskQueue taskQueue) {
		status = STATUS.RUNNING;
		this.taskQueue = taskQueue;
		for (int i = 0; i < COUNT; i++) {
			Executor executor = new ExecutorImpl(taskQueue, (ThreadPool) this);
			pool.add(executor);
			Thread thread = new Thread(executor);
			thread.start();
		}
	}

	public void startPool() {
		/*
		 * for(int i=0;i<pool.size();i++){ pool.get(i).startTask(); }
		 */
	}

	public void stopPool() {
		synchronized (pool) {
			status = STATUS.FINISHED;
			pool.notifyAll();
			taskQueue.stopQueue();
			pool.clear();
		}
	}

	public boolean isRunning() {
		return status.equals(STATUS.RUNNING);
	}

	public boolean isStop() {
		return status.equals(STATUS.FINISHED);
	}

	public void addThread(Executor executor) {
		synchronized (pool) {
			pool.add(executor);
			pool.notify();
		}
	}

	public Executor getThread() {
		Executor res = null;
		synchronized (pool) {
			if (pool.size() > 0) {
				res = pool.get(0);
				pool.remove(0);
			} else {
				try {
					pool.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				res = pool.get(0);
				pool.remove(0);
			}
		}
		return res;
	}
}
