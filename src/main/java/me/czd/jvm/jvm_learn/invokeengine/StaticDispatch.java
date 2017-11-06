package me.czd.jvm.jvm_learn.invokeengine;

/**
 * 由于多态导致编译器和运行期对象的不同，静态类型，和实际类型
 * 这里我们来测试下Overload 代码jvm是怎么来进行静态分配的  也就是我们所说的方法重载！
 * 英文翻译！Method Overload Resolution
 * 
 * 准确的来说，重载是通过参数的静态类型而不是实际类型来判断的
 * resolution 解析
 * 
 * @author Administrator
 *
 */
public class StaticDispatch {
	static abstract class Human{
		
	}
	static class Man extends Human{
		
	}
	static class Woman extends Human{
		
	}
	public void sayHello(Human guy){
		System.out.println("hello,guy");
		
	}
	public void sayHello(Man man){
		System.out.println("hello,gentleman");
	}
	public void sayHello(Woman woman){
		System.out.println("hello,lady");
	}
	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		StaticDispatch sr = new StaticDispatch();
		sr.sayHello(man);
		sr.sayHello(woman);
	}
}
