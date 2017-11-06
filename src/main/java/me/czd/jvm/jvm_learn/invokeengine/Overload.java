package me.czd.jvm.jvm_learn.invokeengine;

import java.io.Serializable;

/**
 * 重载 的优先级！ 一条规则，那个匹配最合适，就是那个，记住会自动转型的,haha
 * 
 * 这里写了无数个重载方法，记住，重载方法并不适用于泛型
 * 
 * @author Administrator
 *
 */
public class Overload {
	public static void sayHello(Object arg) {
		System.out.println("hello Object");
	}

	public static void sayHello(int arg) {
		System.out.println("hello int");
	}

	public static void sayHello(long arg) {
		System.out.println("hello long");
	}

	public static void sayHello(Character arg) {
		System.out.println("hello Character");
	}

	public static void sayHello(char arg) {
		System.out.println("hello char");
	}

	public static void sayHello(char... arg) {

	}

	public static void sayHello(Serializable arg) {
		System.out.println("hello Serializable");
	}

	public static void main(String[] args) {
		//char-> int -> long -> Character -> Serializable -> Object ->char...
		sayHello('a');
	}

}
