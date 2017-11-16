package me.czd.jvm.jvm_learn.jmm;

/**
 * 大话，就是volatile 异变这个关键词 引申，就是如何实现多线程下的计数器 AtomicInteger可以，加锁也可以，试试被
 * 
 * volatile这个很有用并且很有趣的关键字就来了，volatile只能保证可见性，被volatile修饰的变量，就如同cpu遵循的某种协议，这种变量会内存可见的
 * volatile正确的使用场景是，
 * 通过使用volatile变来那个来控制并发，volatile boolean xxx 这样内存可见很好用
 * 
 * @author Administrator
 *
 */
public class VolatileTest {
	public static volatile int race = 0;
	public static final int THREADS_COUNT = 20;

	public static  void increse() {
		//这里涉及的操作比较多，算是多个操作把
		race++;
	}

	public static void main(String[] args) {
		//可以尝试使用线程池来操作，嘿嘿！
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10000; j++) {
					increse();
				}
			});
			threads[i].start();
		}
		//只要还有线程存货，那么当前线程main就yield 后备
		while(Thread.activeCount() > 1){
			Thread.yield();
		}
		//结果总是不等于200000 因此volatile并不能保证原子性
		//如果去掉volatile，那么race更少
		System.out.println(race);
	}
}
