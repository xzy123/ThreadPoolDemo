package interfaces;

public interface ThreadPool {
	
	public void startPool();
	
	public void stopPool();
	
	public boolean isRunning();
	
	public void addThread(Executor executor);
	
	public Executor getThread();
}
