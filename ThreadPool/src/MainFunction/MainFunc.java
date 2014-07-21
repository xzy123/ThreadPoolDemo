package MainFunction;

import impl.TaskImpl;
import impl.TaskQueueImpl;
import impl.ThreadPoolImpl;
import interfaces.Executor;
import interfaces.Task;
import interfaces.TaskQueue;
import interfaces.ThreadPool;

public class MainFunc {
	public static void main(String[] args) throws Exception{
		TaskQueue taskQueue = new TaskQueueImpl();
		for(int i=1;i<=15;i++){
			Task task = new TaskImpl(i);
			taskQueue.addTask(task);
		}
		
		ThreadPool threadPool = new ThreadPoolImpl(taskQueue);
		Thread.sleep(2000);
		for(int i=0;i<10;i++){
			Executor ex = threadPool.getThread();
			//System.out.println(ex);
			ex.startTask();
		}
		
		//threadPool.startPool();
		Thread.sleep(1000);
		threadPool.stopPool();
	}
}
