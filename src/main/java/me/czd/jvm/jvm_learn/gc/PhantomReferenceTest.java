package me.czd.jvm.jvm_learn.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

/**
 * 虚引用，也是幽灵引用，我们这里来一个demo深入了解
 * 首先package-info 中也看到了虚引用基本没有什么用，就是垃圾回收的时候有一个通知！
 * 总结下，就是我们可以声明虚引用来引用我们感兴趣的对象，然后gc在回收的时候，gc垃圾回收器会把这个对象添加
 * 到ReferenceQueen中，这样我们如果检测到ReferenceQueen中有我们感兴趣的对象的时候，说明gc将要
 * 回收这个对象了，此时我们可以在gc回收之前做一些其他事情，比如记录日志等等
 * 
 * 然后通过代码总结就是虚引用需要和ReferenceQueen来进行碰头
 * 虚引用，是让一个引用编程虚引用，然后和一个referenceQueue配合
 * @author Administrator
 *
 */
public class PhantomReferenceTest {
	public static boolean isRun = true;
	public static void main(String[] args) throws InterruptedException {
		String abc = new String("abc");
		System.out.println(abc.getClass() + "@" + abc.hashCode());
		final ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
		new Thread(() -> {
			while(isRun){
				//这里面是一只进行的，直到gc回收后，虚引用对象进入referenceQueue中，
				Object object = referenceQueue.poll();
				if(object != null){
					try {
						Field reField = Reference.class.getDeclaredField("referent");
						reField.setAccessible(true);
						Object result = reField.get(object);
						System.out.println("gc will collect : " + result.getClass()
						+ "@" + result.hashCode() + "\t" + (String)result);
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
		abc = null;
		Thread.currentThread().sleep(3000);
		System.gc();
		Thread.currentThread().sleep(3000);
		isRun = false;
	}
}
