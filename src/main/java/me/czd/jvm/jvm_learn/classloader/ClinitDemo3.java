package me.czd.jvm.jvm_learn.classloader;

/**
 * 虚拟机会保证一个类的<clinit>方法在多线程环境中被正确的加载，同步。 如果多个线程同时去初始化一个类，那么只会有一个线程去执行<clinit>方法
 * 这会导致一个问题，如果<clinit>方法执行时间很长，就会导致其他线程阻塞
 * 
 * 如果 static代码块中有循环代码，会编译错误 Initializer does not complete normally
 * 例如这段代码如果去掉if(true)就会报错
 * 
 * 这段代码就阻塞了
 * 
 * @author Administrator
 *
 */
public class ClinitDemo3 {
	static class DeadLoopClass {
		static {
			if (true) {
				System.out.println(Thread.currentThread() + " init DeadLoopClass!");
				while (true) {
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Runnable script = () -> {
			System.out.println(Thread.currentThread()+"start");
			//类初始化
			DeadLoopClass dlc = new DeadLoopClass(); 
			System.out.println(Thread.currentThread()+"run over");
		};
		new Thread(script,"线程1").start();
		new Thread(script,"线程2").start();
	}
}
