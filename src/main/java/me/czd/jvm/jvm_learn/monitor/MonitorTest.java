package me.czd.jvm.jvm_learn.monitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这里我们通过程序来了解如何监控内存，以及工具的使用
 * 这里我们使用的是 JConsole 来监控的 ，直接cmd jconsole 就可以打开，然后去观察
 * 里面有很多的信息，仔细去品味
 * 内存空间，gc时间，线程数量等等
 * @author Administrator
 *
 */
public class MonitorTest {
	
	static class OOMObject{
		public byte[] placeholder = new byte[64*1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		list = null;
		System.gc();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		fillHeap(1000);
		//保证main线程不结束
		System.in.read();
	}
	
}
