package cn.nihility.designpattern.decorator;

public class Skills implements Hero {
	// 持有一个英雄对象接口
	private Hero hero;

	public Skills(Hero hero) {
		this.hero = hero;
	}

	@Override
	public void learnSkills() {
		if (hero != null)
			hero.learnSkills();
	}
}
