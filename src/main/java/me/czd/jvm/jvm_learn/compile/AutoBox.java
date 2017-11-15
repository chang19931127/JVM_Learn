package me.czd.jvm.jvm_learn.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 另外一个语法糖，就是自动拆装箱 首先作为面向对象语言为什么支持基本数据类型？ 其次，这个语法糖就针对基本数据类型的
 * 
 * @author Administrator
 *
 */
public class AutoBox {
	public static void main(String[] args) {
//		listBox();
		complex();
		autobox();
	}



	private static void autobox() {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 128;
		Integer f = 128;
		Long g = 3L;
		//等号不遇到算数运算的情况下不会自动拆箱，以及他们的equal方法不处理数据转型的关系
		//因此编码中，避免使用自动装箱和拆箱，引起麻烦
		System.out.println(c == d);// true
		//这里涉及到了享元模式(-128,127)
		System.out.println(e == f);// false
		System.out.println(c == (a + b)); // true
		System.out.println(c.equals(a + b));// true
		System.out.println(g == (a + b));// true
		//long != int
		System.out.println(g.equals(a + b));// false
	}

	
	
	private static void complex() {
		List list = Arrays.asList(
		        new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4) });
		int sum = 0;
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			int i = ((Integer) localIterator.next()).intValue();
			sum += i;
		}
		System.out.println(sum);
	}

	private static void listBox() {
		// 这个是Arrays中的内部类，这个list 不能remove 和 add 查看远吗
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		int sum = 0;
		// foreach语法糖，帮助我们迭代数据和Interator对象，
		// 同样，语法糖的情况下，不要删除和添加元素,list1和list都试试把
		for (int i : list1) {
			sum += i;
			list1.add(3);
		}
		System.out.println(sum);
	}

}
