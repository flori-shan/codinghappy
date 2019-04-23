package cn.nihility.designpattern.decorator;

public class BlindMonk implements Hero {

	private String name;

	public BlindMonk(String name) {
		this.name = name;
	}

	@Override
	public void learnSkills() {
		System.out.println(name + "学习了以上技能！");
	}

}
