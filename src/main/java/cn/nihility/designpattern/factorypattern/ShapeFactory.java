package cn.nihility.designpattern.factorypattern;

public class ShapeFactory {

	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		return null;
	}

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
	
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		// 获取 Circle 的对象，并调用它的 draw 方法
		Shape shape1 = shapeFactory.getShape(ShapeEnum.CIRCLE);
		shape1.draw();

		// 获取 Rectangle 的对象，并调用它的 draw 方法
		Shape shape2 = shapeFactory.getShape(ShapeEnum.RECTANGLE);
		shape2.draw();

		// 获取 Square 的对象，并调用它的 draw 方法
		Shape shape3 = shapeFactory.getShape(ShapeEnum.SQUARE);
		shape3.draw();
	}

	public static void main1(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		// 获取 Circle 的对象，并调用它的 draw 方法
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		shape1.draw();

		// 获取 Rectangle 的对象，并调用它的 draw 方法
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		shape2.draw();

		// 获取 Square 的对象，并调用它的 draw 方法
		Shape shape3 = shapeFactory.getShape("SQUARE");
		shape3.draw();
	}

}
