package me.czd.jvm.jvm_learn.classloader;

/**
 * <clinit>()方法是由编译器自动手机类中的所有类变量的复制动作和静态语句块中的语句合并产生的
 * 编译器收集的顺序是由语句在源文件中出现的顺序所决定的，静态语句块中只能访问到定义在静态语句块
 * 之前的变量，定义在它之后的变量，在前面的静态语句块可以赋值，但是不能那个访问
 * 
 * @author Administrator
 *
 */
public class ClinitDemo1 {
	static {
//感觉这里赋值并没有什么卵用
		i = 0;
//只能赋值，不能访问
// error
//		System.out.println(i);
	}
	static int i = 1;
	
	public static void main(String[] args) {
		System.out.println(ClinitDemo1.i);
	}

}
