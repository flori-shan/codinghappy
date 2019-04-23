package cn.nihility.designpattern.decorator;

public class SkillE extends Skills {

	private String skillName;

	public SkillE(Hero hero, String skillName) {
		super(hero);
		this.skillName = skillName;
	}

	@Override
	public void learnSkills() {
		System.out.println("学习了技能E:" + skillName);
		super.learnSkills();
	}

}
