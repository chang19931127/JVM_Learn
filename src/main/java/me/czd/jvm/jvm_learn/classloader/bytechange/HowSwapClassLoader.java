package me.czd.jvm.jvm_learn.classloader.bytechange;


/**
 * 类加载器
 * @author Administrator
 *
 */
public class HowSwapClassLoader extends ClassLoader {
	
	public HowSwapClassLoader(){
		super(HowSwapClassLoader.class.getClassLoader());
	}
	
	public Class<?> loadClassByByte(byte[] bytes){
		return defineClass(null, bytes, 0, bytes.length);
	}

}
