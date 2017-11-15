package me.czd.jvm.jvm_learn.codegenerator.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class JdkDynamicProxy implements InvocationHandler {

	private Object originalObj;

	public Object bind(Object originalObj) {
		this.originalObj = originalObj;
		return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(),
		        this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("welcome");
		Object object = method.invoke(originalObj, args);
		System.out.println("proxy end");
		return object;
	}

}
