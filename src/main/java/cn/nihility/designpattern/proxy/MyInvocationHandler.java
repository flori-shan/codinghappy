package cn.nihility.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	Subject realSubject;

	public MyInvocationHandler(Subject realSubject) {
		this.realSubject = realSubject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Invoke proxy class.");
		
		if (method.getName().equals("sellBooks")) {
			int invokeValue = (int) method.invoke(realSubject, args);
			System.out.println("Invoke sellBooks method.");
			return invokeValue;
		} else if (method.getName().equals("speak")) {
			String speak = (String) method.invoke(realSubject, args);
			System.out.println("Invoke speak method.");
			return speak;
		}
		return null;
	}

}
