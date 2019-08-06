package com.rik.dubbo.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MasterThead {
	private ConcurrentLinkedQueue<Task> linkedQueue = new ConcurrentLinkedQueue<Task>();
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	private Map<String, Thread> workers = new HashMap<String, Thread>();
	
	public MasterThead(WorkThead workThead, int workCount){
		workThead.setWorkQueue(this.linkedQueue);
		workThead.setResultMap(this.resultMap);
		
		for (int i = 0; i < workCount; i++) {
			workers.put("work-"+i, new Thread(workThead));
		}
	}
	
	public void submit(Task task){
		this.linkedQueue.add(task);
	}
	
	public void execute(){
		for (Map.Entry<String, Thread> entry : workers.entrySet()) {
			entry.getValue().start();
		}
	}
	
	public boolean isComplete(){
		for (Map.Entry<String, Thread> entry : workers.entrySet()) {
			if (entry.getValue().getState()!=Thread.State.TERMINATED) {
				return false;
			}
		}
		return true;
	}
	
	public int getResultCount(){
		return resultMap.size();
	}
	
	public void love(){
		long start= System.currentTimeMillis();
		while (true) {
			if (isComplete()) {
				long spent = System.currentTimeMillis()-start;
				System.out.println("最终："+getResultCount()+",执行时间："+spent);
				break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		MasterThead masterThead = new MasterThead(new WorkThead(), Runtime.getRuntime().availableProcessors());
		Task task  = new Task();
		for (int i = 0; i < 20; i++) {
			task = new Task();
			task.setTaskId(""+i);
			task.setTaskName("task-"+i);
			masterThead.submit(task);
		}
		masterThead.execute();
		masterThead.love();
	}
}
