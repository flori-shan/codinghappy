package cn.nihility.designpattern.abstractfactory;

public class Green implements Color {

	@Override
	public void fill() {
		System.out.println("Green::fill().");
	}

}
