package cn.nihility.designpattern.abstractfactory;

public class Red implements Color {

	@Override
	public void fill() {
		System.out.println("Red::fill().");
	}

}
