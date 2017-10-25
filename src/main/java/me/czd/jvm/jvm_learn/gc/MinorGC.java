package me.czd.jvm.jvm_learn.gc;

/**
 * 新生代GC 也就是MinorGC vm args: -Xmx20m -Xms20m -Xmn10m -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8 -verbose:gc SurvivorRatio 这个参数指明了 Eden占用新生带的几成 8 就是8:1:1
 * -XX:+UseParNewGC
 * 新生代使用的是ParallelGC的时候没有发生MinorGC
 * 新生代使用的是ParNewGC的时候发生了MinorGC
 * 这里也就刚好可以看出两个并发的收集器的区别
 * 
 * 
 * @author Administrator
 *
 */
public class MinorGC {
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		testAllocation();
	}

	private static void testAllocation() {
		byte[] allocation1;
		byte[] allocation2;
		byte[] allocation3;
		byte[] allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		//出现GC
		allocation4 = new byte[4 * _1MB];
	}
}
