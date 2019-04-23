package cn.nihility.designpattern.factorypattern;

/**
 * Mouse 的简单工厂类，就是专门生产某个产品的类
 * @author yzx
 *
 */
public class MouseSimpleFactory {

	public static Mouse createMouse(MouseEnum me) {
		if (null == me) {
			return null;
		}
		
		switch (me) {
		case LENOVO:
			return new LenovoMouse();
		case DELL:
			return new DellMouse();
		default:
			return null;
		}
		
	}
	
}
