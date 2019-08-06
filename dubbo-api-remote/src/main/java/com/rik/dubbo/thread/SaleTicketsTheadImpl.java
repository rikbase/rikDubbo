package com.rik.dubbo.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Description;

import com.rik.dubbo.factory.NameTheadFactory;

@Description("多线程实现数据共享案例")
public class SaleTicketsTheadImpl implements Runnable{
	
	private int tickets = 0;
	private int saled_ticket = 10000;  //售票上限；
	private void supplyTicket(int num){
		synchronized (SaleTicketsTheadImpl.class) {
			tickets += num;
			System.out.println(Thread.currentThread().getName()+": supply: "+num+" tickets!");
		}
	}
	
	private void saleTicket(){
		synchronized (SaleTicketsTheadImpl.class) {
			int num = saled_ticket <50 ?(int)(Math.random()*10+1):(int)(Math.random()*100+1);
			if (tickets>=num) {
				tickets-=num;
				saled_ticket-=num;
				System.out.println(Thread.currentThread().getName()+": sales "+num+" tickets, left: "+tickets+" tickets!, saled "+(10000-saled_ticket)+" tickets");
			}else if(tickets<num){
				System.out.println(Thread.currentThread().getName()+": Sorry, no "+num+" tickets left, Please wait a moment.");
			}
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		if(tickets == 0) supplyTicket(2000);
		while (saled_ticket > 0) {
			saleTicket();
			//少于3张，售票中心补票10张；
			if (tickets < 10 && saled_ticket >10) {
				supplyTicket(1000);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+": Ticket Selling stopped ");
	}
	
	public static void startSale(int saleSite){
		final SaleTicketsTheadImpl saleTheadImpl =  new SaleTicketsTheadImpl();
		/*corePoolSize: 核心线程数
		  maximumPoolSize: 最大线程数
		  keepAliveTime: 除核心线程之外的新城的存活时间
		  unit: keepAliveTime单位;
		  workQueue: 用于缓存的队列，默认是链表结构;
		  threadFactory: 线程工厂;
		*/
		ThreadPoolExecutor salePool = new ThreadPoolExecutor(10, saleSite, 
				1L, TimeUnit.NANOSECONDS,
				new LinkedBlockingQueue<Runnable>(500),new NameTheadFactory("Sale"));
		/*常用四大线程池
		 *查看源码得知：
		 *Executors.newFixedThreadPool 实际上就是上述代码，
		 *但是ExecutorService 继承了执行器， 没有 allowCoreThreadTimeOut属性，
		 *而ThreadPoolExecutor与ExecutorService关系：
		 *ThreadPoolExecutor extends AbstractExecutorService implements ExecutorService extends Executor; 
		 **/
//		Executors.newCachedThreadPool();
//		ExecutorService executorService=Executors.newFixedThreadPool(saleSite, new NameTheadFactory("Sale"));
		
		for (int i = 0; i < saleSite; i++) {
			salePool.execute(saleTheadImpl);
		}
		// allowCoreThreadTimeOut 只有在keepAliveTime 大于 0 的时候才能用，
		if (salePool.getKeepAliveTime(TimeUnit.NANOSECONDS) > 0) salePool.allowCoreThreadTimeOut(true);
		if (salePool.isShutdown()) {
			System.out.println("Sale Sites is Closed.");
		}
	}
	
	public static void main(String[] args) {
		startSale(10);
	}
}
