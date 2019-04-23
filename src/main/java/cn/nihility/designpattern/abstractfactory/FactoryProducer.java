package cn.nihility.designpattern.abstractfactory;

import cn.nihility.designpattern.factorypattern.Shape;
import cn.nihility.designpattern.factorypattern.ShapeEnum;

public class FactoryProducer {

	public static AbstractFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("SHAPE")) {
			return new ShapeFactory();
		} else if (choice.equalsIgnoreCase("COLOR")) {
			return new ColorFactory();
		}
		return null;
	}

	public static void main(String[] args) {
		// 获取形状工厂
		AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

		if (null == shapeFactory) {
			System.out.println("shape factory is null, return.");
			return;
		}

		// 获取形状为 Circle 的对象
		Shape shape1 = shapeFactory.getShape(ShapeEnum.CIRCLE);
		shape1.draw();

		// 获取形状为 Rectangle 的对象
		Shape shape2 = shapeFactory.getShape(ShapeEnum.RECTANGLE);
		shape2.draw();

		// 获取形状为 Square 的对象
		Shape shape3 = shapeFactory.getShape(ShapeEnum.SQUARE);
		shape3.draw();

		// 获取颜色工厂
		AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

		if (null == colorFactory) {
			System.out.println("color factory is null, return.");
			return;
		}

		// 获取颜色为 Red 的对象
		Color color1 = colorFactory.getColor(ColorEnum.RED);
		color1.fill();

		// 获取颜色为 Green 的对象
		Color color2 = colorFactory.getColor(ColorEnum.GREEN);
		color2.fill();

		// 获取颜色为 Blue 的对象
		Color color3 = colorFactory.getColor(ColorEnum.BLUE);
		color3.fill();
	}

}
