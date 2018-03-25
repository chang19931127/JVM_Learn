package me.czd.jvm.jvm_learn.jmm.singleton;

/**
 * 通过类加载机制来实现
 * @author Administrator
 *
 */
public class LazyStaticSingleton {
	
	public static LazyStaticSingleton INSTANCE = LazyStaticSingletonHolder.INSTANCE;
	
	// 内部私有类的static 来保证类被加载,就饿汉式加载
	private static class LazyStaticSingletonHolder{
		static final LazyStaticSingleton INSTANCE = createLazyStaticSingleton();
		
		static LazyStaticSingleton createLazyStaticSingleton(){
			return new LazyStaticSingleton();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(LazyStaticSingleton.INSTANCE == LazyStaticSingleton.INSTANCE);
	}
}
