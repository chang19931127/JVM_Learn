package me.czd.jvm.jvm_learn.gc;

/**
 * 动态对象年龄判断 为了更好的适应不同程序的内存状态，jvm并不要求年龄一定大于阀值才会进入老年代，
 * 如果在Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半，年龄大于或等于该年龄的对象 就可以进入老年代，无需超过阀值
 * 
 * vm args: -Xmx20m -Xms20m -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDetials
 * -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 * 记住-XX:+UseParNewGC 这个参数
 * 因为-XX:+UseParallerGC 并不需要设置年龄阀值，这个GC会根据吞吐量自动设置新生代
 * 
 * @author Administrator
 *
 */
public class ObjectActiveTenuring {
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {

		byte[] allocation1, allocation2, allocation3, allocation4;

		allocation1 = new byte[_1MB / 4];
		// 当相同年龄的对象大小之和大于 survivor 也会放入老年代
		// allocation1+allocation2大于survivor空间的一半
		allocation2 = new byte[_1MB / 4];

		allocation3 = new byte[_1MB * 4];
		allocation4 = new byte[_1MB * 4];
		allocation4 = null;
		allocation4 = new byte[_1MB * 4];

	}

}
