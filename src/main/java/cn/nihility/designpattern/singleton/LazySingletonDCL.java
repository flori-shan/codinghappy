package cn.nihility.designpattern.singleton;

public class LazySingletonDCL {

	private static volatile LazySingletonDCL instance = null;

	private LazySingletonDCL() {
		if (null != instance) {
			throw new RuntimeException("can not create singleton instance twice.");
		}
		System.out.println("Create LazySingletonDCL instance.");
	}

	/**
	 * DoubleCheckedLocking, 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
	 */
	public static LazySingletonDCL getInstance() {
		if (null == instance) {
			synchronized (LazySingletonDCL.class) {
				if (null == instance) {
					instance = new LazySingletonDCL();
				}
			}
		}
		return instance;
	}

	public void say() {
		System.out.println("LazySingletonDCL say hello.");
	}

}
