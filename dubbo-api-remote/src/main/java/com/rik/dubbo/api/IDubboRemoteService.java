package com.rik.dubbo.api;

import java.io.Serializable;

public interface IDubboRemoteService extends Serializable{
	public void sayHello(String info);
	public Object getdb();
}
