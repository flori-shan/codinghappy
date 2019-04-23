package cn.nihility.designpattern.abstractfactory;

import cn.nihility.designpattern.factorypattern.Shape;
import cn.nihility.designpattern.factorypattern.ShapeEnum;

public abstract class AbstractFactory {

	public abstract Shape getShape(ShapeEnum shapeEnum);

	public abstract Color getColor(ColorEnum colorEnum);

}
