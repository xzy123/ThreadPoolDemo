package impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Util.STATUS;

import interfaces.Task;
import interfaces.TaskQueue;

public class TaskQueueImpl implements TaskQueue {

	private LinkedList<Task> list = new LinkedList<Task>();
	
	
	public synchronized void addTask(Task task) {
		list.add(task);
	}

	public synchronized Task getTask() {
		Iterator<Task> ite = list.iterator();
		while(ite.hasNext()){
			Task task = ite.next();
			if(task.getStatus().equals(STATUS.NEW)){
				task.setStatus(STATUS.RUNNING);
				return task;
			}
		}
		return null;
	}

	public synchronized void finishTask(Task task) {
		if(task != null && task.getStatus() == STATUS.RUNNING){
			task.setStatus(STATUS.FINISHED);
			System.out.println(((TaskImpl)task).flag + "  is stop ...");
			list.remove(task);
		}
	}

	public synchronized void stopQueue(){
		for(int i=0;i<list.size();i++){
			list.get(i).setStatus(STATUS.FINISHED);
		}
		list.clear();
	}
}
