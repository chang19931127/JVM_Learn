package me.czd.jvm.jvm_learn.classcode;

/**
 * 通过编译这段代码，去查看编译后的Class文件，了解JVM运行的Class文件的构成，
 * 
 * javap -verbose TestClass
 * 获这通过16进制查看代码，哈哈
 * 
 * 参考Java虚拟机规范，官网有，下面
 * 
 * @author Administrator
 *
 */
public class TestClass {
	private int m;

	public int inc() {
		return m + 1;
	}

	public int inc1() {
		int x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
		}
	}
	
	void onlyMe(Object obj){
		synchronized(obj){
			System.out.println("!");
		}
	}
}
