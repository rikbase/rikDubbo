package com.rik.dubbo.thread;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class NIOImpl {
	SocketChannel socketChannel;
	public NIOImpl() throws IOException{
		socketChannel = SocketChannel.open();
//		socketChannel.connect(remote);
		socketChannel.blockingLock();
	}
	
}
