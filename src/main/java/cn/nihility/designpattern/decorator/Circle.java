package cn.nihility.designpattern.decorator;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Circle inside::draw().");
	}

}
