package interfaces;

public interface Executor extends Runnable{
	 Task getTask();
	 
	 void setTask(Task t);
	 
	 void startTask();
}
