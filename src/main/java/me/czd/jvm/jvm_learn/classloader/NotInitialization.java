package me.czd.jvm.jvm_learn.classloader;

/**
 * 被动引用某个类，并不会使得这个类被初始化 初始化的类，意味着等待随时可以被使用
 * 
 * 这里我们还可以明白一个java文件中可以有多个class 但是只有一个public的
 * 这里还有一个关键，就是 static修饰的是类级别的，而不是对象级别的，这个应该明白
 * 
 * 加载了什么类，初始化了什么类
 * 明白大概什么时候会导致类的加载，并且初始化，
 * vm args -verbose:class
 * @author Administrator
 *
 */
public class NotInitialization {
	public static void main(String[] args) {
		//这里仅仅用的是类级别的，并没有使用SubClass这个类
		System.out.println(SubClass.value);
		
		//new， 反射， 子类初始化的父类，执行了main函数的类，MethodHandle实例对应的句柄
//		System.out.println(new SubClass());
		
		//数组定义不会出发类的初始化 因为数组是特殊的类
//		SuperClass[] sca = new SuperClass[10];
//		System.out.println(sca.getClass());
		
		//直接引用常量池 也不会触发类的初始化
//		System.out.println(SuperClass.HELLOWORLD);
	}
}

class SuperClass {
	static {
		System.out.println("SuperClass init");
	}

	public static int value = 123;
	public static final String HELLOWORLD = "hello world";
}

class SubClass extends SuperClass{
	static{
		System.out.println("SubClass init");
	}
}
