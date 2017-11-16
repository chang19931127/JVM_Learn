package me.czd.jvm.jvm_learn.jmm.singleton;

/**
 * 老生常态的单例模式！ 这里暂给出最经典的，并且可以通过有没有volaitle 来查看字节码的不同，(JIT编译的) 如何禁止指令重排序的
 * 
 * @author Administrator
 *
 */
public class DCLSingleton {
	private volatile static DCLSingleton instance;

	//俗称DCL
	public static DCLSingleton getInstance() {
		if (instance == null) {
			synchronized (DCLSingleton.class) {
				if (instance == null) {
					instance = new DCLSingleton();
				}
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
		//测试把
	}
}
