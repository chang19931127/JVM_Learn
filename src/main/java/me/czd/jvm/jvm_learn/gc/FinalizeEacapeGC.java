package me.czd.jvm.jvm_learn.gc;

/**
 * 即便GCRoot 不可达也不一定会被GC回收，一些对象实现了finalize方法称为finalize对象
 * 这些对象会被JVM进行标记，并注册到Finalizer类中，有一条线程专门负责去处理这样的对象去执行finalize方法
 * 来进行一次自我救赎，如果在finalize方法中被GCRoot引用了，那么就逃离了GC的回收
 * 
 * vm args：-XX:+PrintGC -XX:+PrintGCDetails
 * @author Administrator
 *
 */
public class FinalizeEacapeGC {
	public static FinalizeEacapeGC SAVE_HOOK = null;

	public void isAlive() {
		System.out.println("yes,i am still alive!!");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed!!");
		// 可以逃离回收
		FinalizeEacapeGC.SAVE_HOOK = this;
	}

	public static void main(String[] args) throws Exception {
		SAVE_HOOK = new FinalizeEacapeGC();

		// 为null 然后 System.gc
		SAVE_HOOK = null;
		//gc不太稳定，所以给他一个缓冲
		System.gc();
		Thread.sleep(500);

		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no,i am dead!");
		}

		//finalize方法已经被调用一次了，这次就GG
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);

		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no,i am dead!");
		}
	}
}
