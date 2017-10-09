package me.czd.jvm.jvm_learn.gc;

/**
 * 查看JVM是否使用的是引用计数来进行GC
 * 
 * 引用计数算法没有办法解决对象之间的循环引用
 * 		这个可以引申到spring中的循环注入
 * vm args: -XX:+PrintGC -XX:+PrintGCDetails
 * @author Administrator
 *
 */
public class ReferenceCountGC {
	
	public Object instance = null;
	private static final int _1MB = 1024 * 1024;
	private byte[] bigSize = new byte[_1MB];

	public static void main(String[] args) {
		ReferenceCountGC objA = new ReferenceCountGC();
		ReferenceCountGC objB = new ReferenceCountGC();
		objA.instance = objB;
		objB.instance = objA;
		objA = null;
		objB = null;
		
		//假设在这行发生gc，看对象能否被gc
		System.gc();
	}
}
