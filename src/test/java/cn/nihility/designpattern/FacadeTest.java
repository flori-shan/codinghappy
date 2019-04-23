package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.facade.ShapeMaker;

public class FacadeTest {

	@Test
	public void testFacade() {
		ShapeMaker shapeMaker = new ShapeMaker();

		shapeMaker.drawCircle();
		shapeMaker.drawRectangle();
		shapeMaker.drawSquare();
	}

}
