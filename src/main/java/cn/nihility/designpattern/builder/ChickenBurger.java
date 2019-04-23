package cn.nihility.designpattern.builder;

public class ChickenBurger extends Burger {

	@Override
	public String name() {
		return "ChickenBurger";
	}

	@Override
	public float price() {
		return 26.0F;
	}

}
