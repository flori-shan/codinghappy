package cn.nihility.designpattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import cn.nihility.designpattern.singleton.LazySingletonDCL;
import cn.nihility.designpattern.singleton.LazySingletonSafe;

public class SingletonTest {
	
	@Test
	public void testUnsafeSingleton() {
		for (int i = 0; i < 1000; i++) {
			new ST().start();
		}
	}
	
	@Test
	public void testCheatSingleton() {
		
		try {
			Class<?> clazz = Class.forName("cn.nihility.designpattern.singleton.LazySingletonDCL");
			
			@SuppressWarnings("unchecked")
			Constructor<LazySingletonDCL> constructor = (Constructor<LazySingletonDCL>) clazz.getDeclaredConstructor();
			
			constructor.setAccessible(true);
			
			LazySingletonDCL l1 = constructor.newInstance();
			LazySingletonDCL l2 = constructor.newInstance();
			
			System.out.println(l1);
			System.out.println(l2);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	static class ST extends Thread {
		@Override
		public void run() {
			System.out.println(LazySingletonSafe.getInstance());
		}
	}

}
