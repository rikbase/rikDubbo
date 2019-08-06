package com.rik.dubbo.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Description;

import com.rik.dubbo.factory.NameTheadFactory;

@Description("���߳�ʵ�����ݹ�����")
public class SaleTicketsTheadImpl implements Runnable{
	
	private int tickets = 0;
	private int saled_ticket = 10000;  //��Ʊ���ޣ�
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
			//����3�ţ���Ʊ���Ĳ�Ʊ10�ţ�
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
		/*corePoolSize: �����߳���
		  maximumPoolSize: ����߳���
		  keepAliveTime: �������߳�֮����³ǵĴ��ʱ��
		  unit: keepAliveTime��λ;
		  workQueue: ���ڻ���Ķ��У�Ĭ��������ṹ;
		  threadFactory: �̹߳���;
		*/
		ThreadPoolExecutor salePool = new ThreadPoolExecutor(10, saleSite, 
				1L, TimeUnit.NANOSECONDS,
				new LinkedBlockingQueue<Runnable>(500),new NameTheadFactory("Sale"));
		/*�����Ĵ��̳߳�
		 *�鿴Դ���֪��
		 *Executors.newFixedThreadPool ʵ���Ͼ����������룬
		 *����ExecutorService �̳���ִ������ û�� allowCoreThreadTimeOut���ԣ�
		 *��ThreadPoolExecutor��ExecutorService��ϵ��
		 *ThreadPoolExecutor extends AbstractExecutorService implements ExecutorService extends Executor; 
		 **/
//		Executors.newCachedThreadPool();
//		ExecutorService executorService=Executors.newFixedThreadPool(saleSite, new NameTheadFactory("Sale"));
		
		for (int i = 0; i < saleSite; i++) {
			salePool.execute(saleTheadImpl);
		}
		// allowCoreThreadTimeOut ֻ����keepAliveTime ���� 0 ��ʱ������ã�
		if (salePool.getKeepAliveTime(TimeUnit.NANOSECONDS) > 0) salePool.allowCoreThreadTimeOut(true);
		if (salePool.isShutdown()) {
			System.out.println("Sale Sites is Closed.");
		}
	}
	
	public static void main(String[] args) {
		startSale(10);
	}
}
