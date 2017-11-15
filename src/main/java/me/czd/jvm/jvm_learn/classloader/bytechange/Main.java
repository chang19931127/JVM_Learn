package me.czd.jvm.jvm_learn.classloader.bytechange;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 执行类
 * 
 * @author Administrator
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		execute();
	}

	private static void execute() throws Exception {
		DemoExec orgi = new DemoExec();

		HowSwapClassLoader loader = new HowSwapClassLoader();

		InputStream in = Main.class.getResourceAsStream("DemoExec.class");

		byte[] classByte = new byte[in.available()];
		in.read(classByte);
		System.out.println(Arrays.toString(classByte));

		ClassModifier modifier = new ClassModifier(classByte);
		classByte = modifier.modifyUTF8Constant("me/czd/jvm/jvm_learn/classloader/bytechange/DemoTest1", "me/czd/jvm/jvm_learn/classloader/bytechange/DemoTest");
		System.out.println(Arrays.toString(classByte));
		Class<?> osgi = loader.loadClassByByte(classByte);
		// 比较字节码，哈哈，Class就是字节码啊
		osgi.newInstance();
	}
}
