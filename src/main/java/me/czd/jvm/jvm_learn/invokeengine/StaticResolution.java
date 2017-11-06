package me.czd.jvm.jvm_learn.invokeengine;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法静态解析演示，我们需要javap -c 或 -verbose 来查看字节码调用
 * invokestatic
 * invokespecial
 * invokevirtual
 * invokeinterface
 * 这个例子中的上述所有指令都调用了
 * invokedynamic       - 为动态语言提供机制
 * @author Administrator
 *
 */
public class StaticResolution {
	public static void sayHello(){
		System.out.println("hello world");
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("小花");
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("小名");
		StaticResolution.sayHello();
	}
}
