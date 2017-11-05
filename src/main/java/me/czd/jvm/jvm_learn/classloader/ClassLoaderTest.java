package me.czd.jvm.jvm_learn.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 我们自己写一个ClassLoader 加载某个类，然后和ApplicationClassLoader的进行比较 显然结果是不同的
 * 
 * 提倡的写法是 将自己的类加载逻辑写到findClass()方法中来，这样子的类加载器还是符合 双亲委托模型的
 * 因为loadClass中实现了双亲委托逻辑
 * 
 * @author Administrator
 *
 */
public class ClassLoaderTest {
	public static void main(String[] args) throws Exception {
		ClassLoader myLoader = new ClassLoader() {

			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				InputStream is = null;
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					is = getClass().getResourceAsStream(fileName);
					if (is == null) {
						return super.loadClass(name);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return null;
			}

		};

		Object obj = myLoader.loadClass("me.czd.jvm.jvm_learn.classloader.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		// 这里为什么是false，原因是虚拟机此时有两个ClassLoaderTest类，
		// 一个是系统应用程序类加载器加载，一个是我们自定义类加载器加载的，因此是false
		System.out.println(obj instanceof me.czd.jvm.jvm_learn.classloader.ClassLoaderTest);
	}
}
