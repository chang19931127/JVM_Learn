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
 * 还有一个细节在这里面，反编译代理类后，发现代理类继承了Proxy类，但是java中不允许多继承
 * 因此，java提供的jdk动态代理只能代理接口的实现
 * 
 * @author Administrator
 *
 */
public class DynamicProxyTest {

	public static void main(String[] args) throws Exception {
		// 生成代码
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		IHello hello = (IHello) new JdkDynamicProxy().bind(new HelloImpl());
		System.out.println(hello.getClass().getSuperclass());
		FileOutputStream os = new FileOutputStream("d:/$Proxy12.class");
		byte[] file = sun.misc.ProxyGenerator.generateProxyClass("$Proxy12", new Class[] { IHello.class });
		os.write(file);
		os.flush();
		os.close();
		hello.sayHello();
	}
}
