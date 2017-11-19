package me.czd.jvm.jvm_learn.jmm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * jdk提供的新的原子类，自己看源码把了解下CAS的强大
 * 如何判断线程结束，线程池怎么操作，看看吧
 * @author Administrator
 *
 */
public class AtomicTest {
	private static AtomicInteger race = new AtomicInteger(0);
	private static final int THREADS_COUNT = 20;
	
	public static void increase(){
		race.incrementAndGet();
	}
	
	public static void main(String[] args) throws Exception {
		threadPoolExecute();
		threadExecute();		
	}

	private static void threadExecute() {
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < threads.length; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5000; j++) {
					increase();
				}
			}).start();
		}
		while(Thread.activeCount() >1) 
			Thread.yield();
		System.out.println("并发后的结果="+race.get());
	}

	private static void threadPoolExecute() throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
		executorService.execute(() ->{
			for (int i = 0; i < 100000; i++) {
				increase();
			}
		}); 
		executorService.shutdown();
		while(true){
			if(executorService.isTerminated()){
				System.out.println("并发后的结果="+race.get());
				break;
			}
			TimeUnit.MILLISECONDS.sleep(1);
		}
	}
}
