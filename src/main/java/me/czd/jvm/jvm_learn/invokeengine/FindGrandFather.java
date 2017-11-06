package me.czd.jvm.jvm_learn.invokeengine;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 试一下，通过普通的方式可以得到爷爷的方法么?
 * 
 * 我们使用invoke包来解决
 * 
 * @author Administrator
 *
 */
public class FindGrandFather {
	public static void main(String[] args) {
		new FindGrandFather().new Son().thinking();
	}

	class GrandFather {
		void thinking() {
			System.out.println("I am grandfather");
		}
	}

	class Father extends GrandFather {
		void thinking() {
			System.out.println("I am father");
		}
	}

	class Son extends Father {
		void thinking() {
			System.out.println("I am son");
			// 如果调用爷爷的方法
			try {
				MethodType mt = MethodType.methodType(void.class);
				//这里参数一定要写对
				MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt,this.getClass());
				mh.invoke(this);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
}