package me.czd.jvm.jvm_learn.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 使用BTrace来进行程序的动态日志跟踪
 * 在VisualVM中安装了BTrace 然后在我们需要调试的程序右击"Trace Application"
 * 
 * 其实就是埋点，监控方法调用
 * 
 * 我们这里来通过BTace来跟踪本程序
 * @author Administrator
 *
 */
public class BTraceDemo {
	public int add(int a, int b){
		return a+b;
	}
	public static void main(String[] args) throws Exception {
		BTraceDemo bTractDemo = new BTraceDemo();
		BufferedReader reader = new  BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i <10;i++){
			reader.readLine();
			int a = (int) Math.round(Math.random()*1000);
			int b = (int) Math.round(Math.random()*1000);
			System.out.println(bTractDemo.add(a, b));
		}
	}
}

//BTrace script
/*
import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
			@OnMethod(clazz="me.czd.jvm.jvm_learn.monitor.BTraceDemo",method="add",location=@Location(Kind.RETURN))
	public static void func(@Self me.czd.jvm.jvm_learn.monitor.BTraceDemo instance,int a,int b,@Return int result){
		println("调用堆栈：");
		jstack();
		println(strcat("方法参数A:",str(a)));
		println(strcat("方法参数B:",str(b)));
		println(strcat("方法参数 result:",str(result)));
	}
}
*/
