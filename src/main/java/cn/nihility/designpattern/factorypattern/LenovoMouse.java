package cn.nihility.designpattern.factorypattern;

public class LenovoMouse implements Mouse {

	@Override
	public void whoami() {
		System.out.println("Lenovo::say hello.");
	}

}
