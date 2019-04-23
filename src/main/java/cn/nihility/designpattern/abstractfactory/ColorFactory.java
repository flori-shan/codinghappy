package cn.nihility.designpattern.abstractfactory;

import cn.nihility.designpattern.factorypattern.Shape;
import cn.nihility.designpattern.factorypattern.ShapeEnum;

public class ColorFactory extends AbstractFactory {

	@Override
	public Shape getShape(ShapeEnum shapeEnum) {
		return null;
	}

	@Override
	public Color getColor(ColorEnum colorEnum) {
		if (null == colorEnum) {
			return null;
		}
		switch (colorEnum) {
		case RED:
			return new Red();
		case GREEN:
			return new Green();
		case BLUE:
			return new Blue();
		default:
			return null;
		}
	}

}
