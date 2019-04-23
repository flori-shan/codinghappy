package cn.nihility.designpattern.factorypattern;

public class MouseFactory {

	public static Mouse createMouse(MouseEnum me) {
		if (null == me) {
			return null;
		}

		switch (me) {
		case LENOVO:
			LenovoMouseFactory lenovoMouseFactory = new LenovoMouseFactory();
			Mouse lenovoMouse = lenovoMouseFactory.createMouse();
			return lenovoMouse;
		case DELL:
			DellMouseFactory dellMouseFactory = new DellMouseFactory();
			Mouse dellMouse = dellMouseFactory.createMouse();
			return dellMouse;
		default:
			return null;
		}

	}

}
