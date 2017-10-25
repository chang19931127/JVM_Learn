package me.czd.jvm.jvm_learn.gc;

/**
 * 长期存活的对象进入老年代，对象头中有age 和这个参数比较 MaxTenuringThreshold比较
 * 
 * 设置进入老年代的年龄的阀值
 * 
 * vm args: -Xmx20m -Xms20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 * 记住-XX:+UseParNewGC 这个参数
 * 因为-XX:+UseParallerGC 并不需要设置年龄阀值，这个GC会根据吞吐量自动设置新生代
 * 
 * 阀值直接进入老年代
 * 
 * @author Administrator
 *
 */
public class ObjectTenuring {
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[4 * _1MB];
		//设置成1，一次gc后就进入老年代了，和设置的15比较下
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}
}
