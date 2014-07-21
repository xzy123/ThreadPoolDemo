package interfaces;

import Util.STATUS;


public interface Task {
	
	public STATUS getStatus();
	
	public void setStatus(STATUS status);
	
	public void deal();
}
