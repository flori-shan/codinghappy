package cn.nihility.designpattern.builder;

public class Director {

	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public void construct() {
		builder.builderPart1();
		for (int i = 0; i < 5; i++) {
			builder.builderPart2();
		}
		builder.builderPart3();
	}

}
