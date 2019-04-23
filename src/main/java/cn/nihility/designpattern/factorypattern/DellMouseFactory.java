package cn.nihility.designpattern.factorypattern;

public class DellMouseFactory {

	public Mouse createMouse() {
		return new DellMouse();
	}
	
}
