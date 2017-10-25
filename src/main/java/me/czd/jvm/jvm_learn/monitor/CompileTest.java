package me.czd.jvm.jvm_learn.monitor;

/**
 * 来进行一场编译大战
 * 
 * 下载：https://kenai.com/projects/base-hsdis/downloads 貌似不支持Windows
 * 就是hsdis 插件，百度下可以查看反汇编的内容
 * 
 * 输入以下命令，问题是，必须要有插件来支持PrintAssembly
 * java -XX:+PrintAssembly -XX:+UnlockDiagnosticVMOptions -Xcomp -XX:CompileCommand=dontinline,*CompileTest.s
 * um -XX:CompileCommand=compileonly,*CompileTest me.czd.jvm.jvm_learn.monitor.CompileTest
 * 
 * @author Administrator
 *
 */
public class CompileTest {
	int a = 1;
	static int b = 2;

	public int sum(int c) {
		return a + b + c;
	}

	public static void main(String[] args) {
		new CompileTest().sum(3);
	}
}
