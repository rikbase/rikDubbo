package com.rik.dubbo.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NameTheadFactory implements ThreadFactory{
	private static final AtomicInteger poolNumber = new AtomicInteger(1);
	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;
	
	public NameTheadFactory() {
	      //Ä¬ÈÏnamePrefix = default-name-pool
	      this("default-name-pool");
	}
	
	public NameTheadFactory(String name) {
		// TODO Auto-generated constructor stub
		SecurityManager s = System.getSecurityManager();
	    group = (s != null) ? s.getThreadGroup():Thread.currentThread().getThreadGroup();
	    this.namePrefix = name + poolNumber.getAndIncrement();
	}
	
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		
		Thread t = new Thread(group, r, namePrefix + "-site-"+threadNumber.getAndIncrement(), 0);
	      if (t.isDaemon())
	         t.setDaemon(false);
	      if (t.getPriority() != Thread.NORM_PRIORITY)
	         t.setPriority(Thread.NORM_PRIORITY);
	      return t;
	}

}
