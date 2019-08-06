package com.rik.dubbo.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WorkThead implements Runnable{
	private ConcurrentLinkedQueue<Task> workQueue;
	private ConcurrentHashMap<String, Object> resultMap;
	
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Task task = workQueue.poll();
			if(task==null) break;
			Object outputObject = handle(task);
			System.out.println(Thread.currentThread().getName()+"正在执行"+outputObject+"任务");
			resultMap.put(task.getTaskId(), outputObject);
		}
	}
	
	private Object handle(Task task){
		Object output = null;
		try {
			Thread.sleep(1000);
			output = task.getTaskName();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	public ConcurrentLinkedQueue<Task> getWorkQueue() {
		return workQueue;
	}
	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}
	public ConcurrentHashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	
		
}
