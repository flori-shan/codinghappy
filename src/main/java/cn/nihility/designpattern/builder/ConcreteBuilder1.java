package cn.nihility.designpattern.builder;

public class ConcreteBuilder1 extends Builder {

	private StringBuilder buffer = new StringBuilder();

	@Override
	public void builderPart1() {
		buffer.append("Builder1 : Part1\n");
	}

	@Override
	public void builderPart2() {
		buffer.append("Builder1 : Part2\n");
	}

	@Override
	public void builderPart3() {
		buffer.append("Builder1 : Part3\n");
	}

	public String result() {
		return buffer.toString();
	}

}
