package cn.nihility.designpattern.abstractfactory;

import cn.nihility.designpattern.factorypattern.Circle;
import cn.nihility.designpattern.factorypattern.Rectangle;
import cn.nihility.designpattern.factorypattern.Shape;
import cn.nihility.designpattern.factorypattern.ShapeEnum;
import cn.nihility.designpattern.factorypattern.Square;

public class ShapeFactory extends AbstractFactory {

	@Override
	public Shape getShape(ShapeEnum shapeEnum) {
		if (shapeEnum == null) {
			return null;
		}
		switch (shapeEnum) {
		case CIRCLE:
			return new Circle();
		case RECTANGLE:
			return new Rectangle();
		case SQUARE:
			return new Square();
		default:
			return null;
		}
	}

	@Override
	public Color getColor(ColorEnum colorEnum) {
		return null;
	}

}
