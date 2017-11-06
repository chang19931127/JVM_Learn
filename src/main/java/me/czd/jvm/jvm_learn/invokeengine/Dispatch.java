package me.czd.jvm.jvm_learn.invokeengine;

/**
 * 这里走一个，多分配的例子 明白java暂时的方法是如何选取的如何分配的 再回到静态分配和动态分配 静态分配
 * 编译器决定了什么？静态对象和重载方法
 * 运行期决定了什么？实际运行对象
 * 
 * @author Administrator
 *
 */
public class Dispatch {
	static class QQ {

	}

	static class _360 {

	}

	public static class Father{
		public void hardChoice(QQ arg){
			System.out.println("father choice qq");
		}
		public void hardChoice(_360 arg){
			System.out.println("father choice _360");
		}
	}
	
	public static class Son extends Father{
		public void hardChoice(QQ arg){
			System.out.println("Son choice qq");
		}
		public void hardChoice(_360 arg){
			System.out.println("Son choice _360");
		}
	}
	
	public static void main(String[] args) {
		Father father = new Father();
		Father son = new Son();
		father.hardChoice(new _360());
		son.hardChoice(new QQ());
	}
}
