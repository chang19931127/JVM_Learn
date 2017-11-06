package me.czd.jvm.jvm_learn.invokeengine;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 一个MethodHandle 的demo
 * lang.invoke给我们提供了几个API提供了动态语言的支持，动态语言，运行时决定对象类型
 * 走你
 * 是不是这里方法和反射有点相似啊
 * 通过MethodType 并且配合MethodHandles.lookup方法来找到方法去执行
 * 这里和反射的不同是反射针对的是java语言，而invoke包内的是站在虚拟机层面上的，因此性能更容易被虚拟机优化
 * 
 * @author Administrator
 *
 */
public class MethodHandleTest {
	static class ClassA{
		public void println(String string){
			System.out.println(string);
		}
	}
	
	private static MethodHandle getPrintlnMH(Object reveiver) throws Exception{
		/**
		 * MethodType 代表方法类型，第一个参数返回值，第二个之后的就是方法参数
		 */
		MethodType methodType = MethodType.methodType(void.class,String.class);
		/**
		 * lookup()方法来自MethodHandles.lookup
		 * 意思是在指定类中找到符合给定MethodType的句柄
		 * bingTo方法来帮助绑定指定对象
		 */
		return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", methodType).bindTo(reveiver);
	}
	
	public static void main(String[] args) throws Exception, Throwable {
		Object obj = System.currentTimeMillis() % 1 == 1  ? System.out : new ClassA();
		getPrintlnMH(obj).invokeExact("开开心心");
	}
}
