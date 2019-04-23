package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.bridge.Circle;
import cn.nihility.designpattern.bridge.GreenCircle;
import cn.nihility.designpattern.bridge.RedCircle;
import cn.nihility.designpattern.bridge.Shape;

public class BirdgeTest {

	@Test
	public void testBirdge() {
		Shape redCircle = new Circle(100, 100, 10, new RedCircle());
		Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

		redCircle.draw();
		greenCircle.draw();
	}

}
