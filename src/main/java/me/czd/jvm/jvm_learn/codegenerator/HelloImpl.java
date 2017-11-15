package me.czd.jvm.jvm_learn.codegenerator;

/**
 * IHello
 * @author Administrator
 *
 */
public class HelloImpl implements IHello {

	@Override
	public void sayHello() {
		System.out.println("hello world");
	}

}
