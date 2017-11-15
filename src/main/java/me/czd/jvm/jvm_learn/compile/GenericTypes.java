package me.czd.jvm.jvm_learn.compile;

import java.util.List;

/**
 * 泛型擦出，虽说泛型会被擦出，但是元数据中还是有泛型的信息，可以通过反射来获取到参数化类型
 * 
 * 泛型遇到重载会如何
 * 
 * 这里 jdk 1.6不会报错，未验证
 * @author Administrator
 *
 */
public class GenericTypes {
	public static void method(List<String> list){
		System.out.println(list);
	}
	
//	编译器报错
//	public static void method(List<Integer> list){
//		System.out.println(list);
//	}

//	编译器报错
//	public static Integer method(List<Integer> list){
//		System.out.println(list);
//		return new Integer(1);
//	}

//	编译器报错
//	public static String method(List<String> list){
//		System.out.println(list);
//		return "string";
//	}

}
