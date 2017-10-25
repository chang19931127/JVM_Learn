package me.czd.jvm.jvm_learn.monitor;

/**
 * 死锁代码的线程查看 同样使用的是jconsole
 * 
 * @author Administrator
 *
 */
public class MonitorDeadLockTest {
	static class SynAddRunnable implements Runnable {
		int a, b;

		public SynAddRunnable(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			synchronized (Integer.valueOf(a)) {
				System.out.println("进入 a 这个同步代码块");
				synchronized (Integer.valueOf(b)) {
					System.out.println("进入b 这个同步代码块");
					System.out.println("a + b = " + (a + b));
				}
			}
		}

	}
	
	public static void main(String[] args) {
		//多操作，会有不同的结果，因为线程的执行时间是不定的，但是100个的话，死锁的概率更大
		System.out.println(Integer.valueOf(1) == Integer.valueOf(1));
//		for (int i = 0; i < 100; i++) {
			new Thread(new SynAddRunnable(1, 2)).start();
			new Thread(new SynAddRunnable(2, 1)).start();
//		}
	}
}
