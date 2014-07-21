package interfaces;

public interface TaskQueue {
	public void addTask(Task task);
	
	public Task getTask();
	
	public void finishTask(Task task);
	
	public void stopQueue();
}
