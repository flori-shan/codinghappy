package cn.nihility.designpattern.facade;

public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Rectangle Inside::draw().");
	}

}
