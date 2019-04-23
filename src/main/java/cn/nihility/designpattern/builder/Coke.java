
package cn.nihility.designpattern.builder;

public class Coke extends ColdDrink {

	@Override
	public String name() {
		return "Coke";
	}

	@Override
	public float price() {
		return 8.0F;
	}

}
