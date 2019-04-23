package cn.nihility.designpattern.factorypattern;

public class LenovoMouseFactory {
	
	public Mouse createMouse() {
		return new LenovoMouse();
	}

}
