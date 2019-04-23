package cn.nihility.designpattern;

import java.lang.reflect.Proxy;

import org.junit.Test;

import cn.nihility.designpattern.proxy.CglibProxy;
import cn.nihility.designpattern.proxy.Engineer;
import cn.nihility.designpattern.proxy.Image;
import cn.nihility.designpattern.proxy.MyInvocationHandler;
import cn.nihility.designpattern.proxy.ProxyImage;
import cn.nihility.designpattern.proxy.RealSubject;
import cn.nihility.designpattern.proxy.Subject;

public class ProxyTest {

	@Test
	public void testProxy() {
		Image image = new ProxyImage("test_10mb.jpg");
		// 图像将从磁盘加载
		image.display();
		System.out.println("---------------");
		// 图像不需要从磁盘加载
		image.display();
	}

	@Test
	public void testJDKSelfProxy() {

		Subject realSubject = new RealSubject();

		MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);

		Subject proxyClass = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class<?>[] { Subject.class }, myInvocationHandler);

		proxyClass.sellBooks();
		proxyClass.speak();

	}

	@Test
	public void testCGLIB() {
		// 生成 Cglib 代理类
		Engineer engineerProxy = (Engineer) CglibProxy.getProxy(new Engineer());
		// 调用相关方法
		engineerProxy.work();
		engineerProxy.eat();
	}

}
