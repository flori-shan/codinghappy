package cn.nihility.designpattern.prototype;

import java.util.Hashtable;

public class ShapeCache {

	private static Hashtable<String, Shape> shapeTable = new Hashtable<>();

	public static Shape getShape(String shape) {
		Shape cachedShape = shapeTable.get(shape);
		return (Shape) cachedShape.clone();
	}

	public static void loadCache() {
		Circle circle = new Circle();
		circle.setId("1");
		shapeTable.put(circle.getId(), circle);

		Square square = new Square();
		square.setId("2");
		shapeTable.put(square.getId(), square);

		Rectangle rec = new Rectangle();
		rec.setId("3");
		shapeTable.put(rec.getId(), rec);
	}

}
