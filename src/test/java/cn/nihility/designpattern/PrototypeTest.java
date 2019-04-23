package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.prototype.Shape;
import cn.nihility.designpattern.prototype.ShapeCache;

public class PrototypeTest {
	
	@Test
	public void testPortotype() {
		ShapeCache.loadCache();
		
		Shape cloneShape = ShapeCache.getShape("1");
		System.out.println("Shape: " + cloneShape.getType());
		
		Shape cloneShape2 = ShapeCache.getShape("2");
		System.out.println("Shape : " + cloneShape2.getType());
		
		Shape cloneShape3 = ShapeCache.getShape("3");
		System.out.println("Shape: " + cloneShape3.getType());
	}

}
