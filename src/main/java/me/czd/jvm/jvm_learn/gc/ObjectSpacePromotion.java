package me.czd.jvm.jvm_learn.gc;

import java.io.IOException;

/**
 * 空间分配担保 空间分配担保什么意思，再发生MinorGC之前，虚拟机会检查老年代最大可用的连续空间是否大于等于新生代所有对象总空间
 * 如果这个条件成立，那么MinorGC是安全的，为什么这么说，是因为MinorGC后，最坏是所有对象进入老年代，那么就
 * 需要老年代能够容纳下这些晋升过来的对象 这个参数HandlePromotionFailure设置值是否允许担保失败，允许担保的话
 * 如果老年代可用空间小于新生代历次晋升到老年代的对象，就要进行一次FullGC,确保对象可以晋升成功
 * 
 * vm args: -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails
 * -XX:-HandlePromotionFailure -XX:SurvivorRatio=8
 * 
 * 这里我们使用cms
 * -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly 
 * -XX:CMSInitiatingOccupancyFraction=75
 * 
 * jdk1.6之后这个参数就移除了，改成了硬性要求
 * 只要老年代的连续空间大于新生代对象总大小或者历次晋升的平均大小就会进行MinorGC，否则将进行FullGC
 * 
 * @author Administrator
 *
 */
public class ObjectSpacePromotion {
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) throws IOException {
		byte[] a1, a2, a3, a4, a5, a6, a7;
		a1 = new byte[2 * _1MB];
		a2 = new byte[2 * _1MB];
		a3 = new byte[2 * _1MB];
//		a1 = null;
//		a4 = new byte[2 * _1MB];
//		a5 = new byte[2 * _1MB];
//		a6 = new byte[2 * _1MB];
//		a4 = null;
//		a5 = null;
//		a6 = null;
		//由于这个新生代对象 4M 没办法晋升到老年代，所以不断出发CMSGC
		a7 = new byte[4 * _1MB];
		System.in.read();
	}
}
