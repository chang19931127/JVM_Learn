package me.czd.jvm.jvm_learn.gc;

/**
 * 大对象直接进入老年代
 * 在ParNewGC的情况下可以使用这个参数	-XX:PretenureSizeThreshold=3145728设置大对象
 * 大对象晋升老年代的阀值1024*1024*3=3145728=3M
 * 这个参数只有Serial和ParNew，也就是说串行收集器和串行收集器的多线程版本才有效
 * 
 * vm args: -Xms20m -Xmx20m -Xmn10m -verbose:gc -XX:+PrintGCDetails 
 * 			-XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * 记住-XX:+UseParNewGC 这个参数
 * 因为-XX:+UseParallerGC 并不需要设置年龄阀值，这个GC会根据吞吐量自动设置新生代
 * 
 * @author Administrator
 *
 */
public class BigObjectPretenure {
	private static final int _1MB = 1024 * 1024;
	
	public static void main(String[] args) {
		byte[] allocation = new byte[4 * _1MB];
	}
}
