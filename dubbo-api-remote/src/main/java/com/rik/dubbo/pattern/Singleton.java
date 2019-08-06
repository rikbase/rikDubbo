package com.rik.dubbo.pattern;

public class Singleton {
	//˫��У����
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
	
	//��̬�ڲ���ʵ��
	private static class SingletonHolder{
		private static final Singleton instance = new Singleton();
	}
	private Singleton(){};
	public static Singleton getInstance(){
		return SingletonHolder.instance;
	}
}
