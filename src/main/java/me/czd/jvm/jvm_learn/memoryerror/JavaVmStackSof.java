package me.czd.jvm.jvm_learn.memoryerror;

/**
 * 如果线程请求的栈深度大于虚拟机允许的最大深度，就抛出StackOverFlowError
 * 方法栈
 * VM args:-Xss128k
 * 
 * @author Administrator
 *
 */
public class JavaVmStackSof {
	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		// 不断的回调
		stackLeak();
	}

	public static void main(String[] args) {
		JavaVmStackSof oom = new JavaVmStackSof();
		try {
			oom.stackLeak();
		} catch (Exception e) {
			System.out.println("stack length = " + oom.stackLength);
			e.printStackTrace();
		}
	}
}
