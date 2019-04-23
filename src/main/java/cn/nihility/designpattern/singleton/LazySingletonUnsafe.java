package cn.nihility.designpattern.singleton;

public class LazySingletonUnsafe {
	
	private static volatile LazySingletonUnsafe instance = null;
	
	private LazySingletonUnsafe() {
		System.out.println("Create LazySingletonUnsafe instance.");
	}
	
	public static LazySingletonUnsafe getInstance() {
		if (null == instance) {
			instance = new LazySingletonUnsafe();
		}
		return instance;
	}

	public void say() {
		System.out.println("LazySingletonUnsafe say hello.");
	}
}
