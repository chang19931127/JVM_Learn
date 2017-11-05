package me.czd.jvm.jvm_learn.classloader;

/**
 * 这里我们了解的是 static 的顺序执行
 * 
 * static 的几种使用都列出来了
 * 字段，方法，代码块，static 修饰的东西属于  类
 * 
 * @author Administrator
 *
 */
public class ClinitDemo2 {
	
	static class Parent {
		public static int A = 1;
		static {
			A = 2;
		}
	}
	
	static class Sub extends Parent{
		public static int B = A;
	}
	
	public static void main(String[] args) {
//		等价
//		System.out.println(ClinitDemo2.Sub.B);
		System.out.println(Sub.B);
	}
}
