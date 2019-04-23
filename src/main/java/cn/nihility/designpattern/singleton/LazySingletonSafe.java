package cn.nihility.designpattern.singleton;

public class LazySingletonSafe {
	
	private static volatile LazySingletonSafe instance = null;
	
	private LazySingletonSafe() {
		System.out.println("create LazySingletonSafe instance.");
	}
	
	/**
	 * 这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
	 */
	public static synchronized LazySingletonSafe getInstance() {
		if (null == instance) {
			instance = new LazySingletonSafe();
		}
		return instance;
	}

}
