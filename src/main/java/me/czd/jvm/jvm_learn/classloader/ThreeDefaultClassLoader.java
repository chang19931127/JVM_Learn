package me.czd.jvm.jvm_learn.classloader;

/**
 * 三种默认的类加载器
 * 
 * 三个默认类加载器的关系
 * 
 * @author Administrator
 *
 */
public class ThreeDefaultClassLoader {
	public static void main(String[] args) {
		System.out.println("启动类加载器=" + Integer.class.getClassLoader());
		
		System.out.println("启动类加载器=" + ThreeDefaultClassLoader.class.getClassLoader().getParent().getParent());
		System.out.println("扩展类加载器=" + ThreeDefaultClassLoader.class.getClassLoader().getParent());
		System.out.println("应用程序类加载器=" + ThreeDefaultClassLoader.class.getClassLoader());
	}
}
