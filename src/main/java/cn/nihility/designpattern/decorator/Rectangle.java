package cn.nihility.designpattern.decorator;

public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Rectangle inside::draw().");
	}

}
