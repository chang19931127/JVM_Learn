package me.czd.jvm.jvm_learn.jmm;

import java.util.Vector;

/**
 * jdk提供的Vector线程安全类，可以看到内部方法都用synchronized来修饰的
 * 但是在某些情况下还是没办法绝对安全！
 * 
 * 可以运行出来 有些线程的操作是有问题的,使用同步来绝对同步
 * 这里有一个迷惑啊，请看length变量，如果单线程，就别每次都计算并且比较了！！！
 * @author Administrator
 *
 */
public class VectorTest {
	private static Vector<Integer> vector = new Vector<>();
	
	public static void main(String[] args) {
		//防止线程过多
		while(Thread.activeCount() < 20){
			for (int i = 0; i < 10; i++) {
				//autobox
				vector.add(i);
			}
			Thread printThread = new Thread(() -> {
//				synchronized(vector){
					for (int i = 0,length = vector.size(); i < vector.size(); i++) {
						System.out.println(vector.get(i));
					}
//				}
			});
			
			Thread removeThread = new Thread(() -> {
//				synchronized(vector){
					for (int i = 0,length = vector.size(); i < vector.size(); i++) {
						vector.remove(i);
//					}
				}
			});
			
			
			printThread.start();
			removeThread.start();
			
		}
		
		
	}
}
