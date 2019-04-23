package cn.nihility.designpattern.prototype;

public class Realizetype implements Cloneable {
	
	public Realizetype() {
		System.out.println("Realize type create successful.");
	}

	public Realizetype clone() throws CloneNotSupportedException {
		System.out.println("clone prototype successful.");
		return (Realizetype) super.clone();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		Realizetype rt = new Realizetype();
		Realizetype rt2 = rt.clone();
		System.out.println("same object " + (rt == rt2));
		
	}

}
