package me.czd.jvm.jvm_learn.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 线程等待示例
 * 可以通过jconsole 来看到线程的信息
 * @author Administrator
 *
 */
public class MonitorWaitTest {
	public static void createBusyThread() {
		// run()方法，start()调用
		// lambda
		Thread thread = new Thread(() -> {
			while (true)
				;
		}, "testBusyThread");
		thread.start();
	}

	public static void createLockThread(final Object lock) {
		//同步代码块
		Thread thread = new Thread(() ->{
			synchronized(lock){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"testLockThread");
		thread.start();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		createBusyThread();
		br.readLine();
		Object object = new Object();
		createLockThread(object);
	}
}
