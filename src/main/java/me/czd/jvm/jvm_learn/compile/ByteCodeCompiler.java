package me.czd.jvm.jvm_learn.compile;

import java.net.URI;
import java.io.IOException;
import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 * 
 * 使用JAVA API 来进行动态编译代码
 * 
 * @version 1.0.0
 * 
 */
public class ByteCodeCompiler {
	
	public static void main(String[] args) throws Exception{
		String source="public class Main { public static void main(String[] args) {System.out.println(\"hello world\");}}";
		String filePath = ByteCodeCompiler.class.getResource("").getPath();
		System.out.println(filePath);
		boolean result=compiler(source, "Main",filePath);
		if(result){
			System.out.println("编译成功");
		}
		Class<?> clazz = Class.forName("Main");
		clazz.getMethod("main",String[].class).invoke(null,(Object)new String[]{"a","v"});
	}
	
	public static boolean compiler(String source,String name,String classFileOutputPath){
		JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager=compiler.getStandardFileManager(null, null, null);
		StringSourceJavaObject javaObj=new StringSourceJavaObject(name, source);
		//指定输出文件路径，当然也可以制定输入文件，总体就是JavaCompile这个类
		Iterable<String> options = Arrays.asList("-d", classFileOutputPath);
		CompilationTask task=compiler.getTask(null, fileManager, null, options, null, Arrays.asList(javaObj));
		return task.call();
	}
}

class StringSourceJavaObject extends SimpleJavaFileObject{

	private String content;
	
	public StringSourceJavaObject(String name ,String content){
		super(URI.create("string:///"+name.replace('.', '/')+Kind.SOURCE.extension), Kind.SOURCE);
		this.content=content;
	}
	
	public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException{
		return this.content;
	}
	
}
