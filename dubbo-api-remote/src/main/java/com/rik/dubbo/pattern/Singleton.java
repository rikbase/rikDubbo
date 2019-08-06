package com.rik.dubbo.pattern;

public class Singleton {
	//双重校验锁
//	private static volatile Singleton instance;
//	private Singleton(){}
//	public static Singleton getInstance() {
//		if (instance==null) {
//			synchronized (Singleton.class) {
//				if (instance == null) {
//					instance = new Singleton();
//				}
//			}
//		}
//		return instance;
//	}
	
	//静态内部类实现
	private static class SingletonHolder{
		private static final Singleton instance = new Singleton();
	}
	private Singleton(){};
	public static Singleton getInstance(){
		return SingletonHolder.instance;
	}
}
