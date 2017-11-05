package me.czd.jvm.jvm_learn.classloader;

/**
 * 字段解析，主要是针对属性，字段的解析，
 * 
 * 继承关系，从下往上搜索，也就是说，总是在覆盖，自底向上查找
 * 
 * @author Administrator
 *
 */
public class FieldResolution {
	interface Interface0 {
		// 和下面int A = 0 没有区别
		public static final int A = 0;
	}

	interface Interface1 extends Interface0 {
		int A = 1;
	}

	interface Interface2 {
		int A = 2;
	}

	static class Parent implements Interface1 {
		public static int A = 3;
	}

	static class Sub extends Parent implements Interface2 {
		//如果这里注释掉，那么Sub.A 就会不知道是父类的还是接口的了
		public static int A = 4;
	}

	public static void main(String[] args) {
		System.out.println("Sub.A = " + Sub.A);
	}
}
