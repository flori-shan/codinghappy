package cn.nihility.designpattern.singleton;

public class SingleObject {
	
	private static SingleObject instance = new SingleObject();
	
	private SingleObject() {}
	
	public static SingleObject getInstance() { return instance; }
	
	public void sayHello() {
		System.out.println("Single Object say hello.");
	}

}
