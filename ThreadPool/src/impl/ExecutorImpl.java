package impl;

import interfaces.Executor;
import interfaces.Task;
import interfaces.TaskQueue;
import interfaces.ThreadPool;

public class ExecutorImpl implements Executor {

	private TaskQueue taskQueue;
	private ThreadPool threadPool;
	private Object lock = new Object();

	public ExecutorImpl(TaskQueue taskQueue, ThreadPool threadPool) {
		this.taskQueue = taskQueue;
		this.threadPool = threadPool;
	}

	public Task getTask() {
		return taskQueue.getTask();
	}

	public void setTask(Task t) {
		taskQueue.addTask(t);
	}

	public void startTask() {
		synchronized (lock) {
			lock.notify();
		}/**/
	}

	public void run() {
		// System.out.println(1);
		while (threadPool.isRunning()) {
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}/**/
			}
			Task task = this.getTask();
			if (task != null) {
				task.deal();
				taskQueue.finishTask(task);
			}

			/*
			 * 执行完任务，将该线程返回线程池
			 */
			threadPool.addThread(this);
			taskQueue.finishTask(task);
		}
	}

}
