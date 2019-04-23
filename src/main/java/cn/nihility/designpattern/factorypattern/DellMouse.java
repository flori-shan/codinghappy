package cn.nihility.designpattern.factorypattern;

public class DellMouse implements Mouse {

	@Override
	public void whoami() {
		System.out.println("DellMouse::say hello.");
	}

}
