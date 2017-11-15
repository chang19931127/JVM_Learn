package me.czd.jvm.jvm_learn.codegenerator.jdkproxy;

import java.io.FileOutputStream;

import me.czd.jvm.jvm_learn.codegenerator.HelloImpl;
import me.czd.jvm.jvm_learn.codegenerator.IHello;

/**
 * 
 * 主要利用的sun.misc.ProxyGenerator来生成字节码 
 * 跟踪一遍代码，可以发现 验证，优化，缓存，同步，生成字节码，显式类加载
 * 详细可以看openJDK jdk\src\share\classes\sun\misc
 * 
 * @author Administrator
 *
 */
public class DynamicProxyTest {

	public static void main(String[] args) throws Exception {
		// 生成代码
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		IHello hello = (IHello) new JdkDynamicProxy().bind(new HelloImpl());
		FileOutputStream os = new FileOutputStream("d:/$Proxy12.class");
		byte[] file = sun.misc.ProxyGenerator.generateProxyClass("$Proxy12", new Class[] { IHello.class });
		os.write(file);
		os.flush();
		os.close();
		hello.sayHello();
	}
}
