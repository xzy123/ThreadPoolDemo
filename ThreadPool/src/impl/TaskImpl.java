package impl;

import interfaces.Task;
import Util.STATUS;

public class TaskImpl implements Task {
	
	int flag;
	
	public TaskImpl(int flag){
		this.flag = flag ;
	}
	
	private STATUS status = STATUS.NEW;
	
	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public void deal() {
		System.out.println(this.flag + "  is working...");
	}

}
