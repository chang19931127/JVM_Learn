package me.czd.jvm.jvm_learn.invokeengine;

import java.io.IOException;

/**
 * 局部变量表的容量以变量槽Slot为最小单位
 * 
 * Slot存在一个重用的说法，就是说在方法执行时，局部变量使用完毕，就会被其他变量的重用地址
 * 但是重用会造成一些映像，我们这里就是来看局部变量表复用对GC的影响
 * 
 * 猜测 int a 这个变量 重用了 placeHolder 的Slot 所以 placeHolder 就被GCRoot 抛弃
 * 在没有这两种修改的时候，GCRoot 一部分的局部变量表仍然保持着对他的关联
 * 
 * vm: -verbose:gc
 * 
 * @author Administrator
 *
 */
public class SlotInfluence {
	public static void main(String[] args) {
		{
			byte[] placeHolder = new byte[64 * 1024 * 1024];
			// 同样这里 = null 也会导致 抛弃placeHolder
			// placeHolder = null;
		}
		// int a = 0 ，那么 抛弃 placeHolder
		// int a = 0;

		System.gc();
	}
}
