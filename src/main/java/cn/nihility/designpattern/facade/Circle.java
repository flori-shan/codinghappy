package cn.nihility.designpattern.facade;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Cirlce Inside::draw().");
	}

}
