package me.czd.jvm.jvm_learn.invokeengine;

/**
 * 动态分派 重写，就是使用的实际类型
 * 生命类型和实际类型，这里也就体现出了虚方法
 * 方法调用的时候jvm需要来判断虚方法的实际对象，但是字段不需要这样
 * 
 * @author Administrator
 *
 */
public class DynamicDispatch {
	static abstract class Human {
		public int a = 0;

		protected abstract void sayHello();
	}

	static class Man extends Human {
		public int a = 1;

		@Override
		protected void sayHello() {
			System.out.println("man say hello +" + a);
		}

	}

	static class Woman extends Human {
		public int a = 2;

		@Override
		protected void sayHello() {
			System.out.println("woman say hello +" + a);
		}

	}

	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		// 根据实际类型，但是字段还是依赖的是静态类型
		System.out.println(man.a);
		System.out.println(woman.a);
		// 根据实际类型
		man.sayHello();
		woman.sayHello();
		man = new Woman();
		man.sayHello();
	}
}
