package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.decorator.BlindMonk;
import cn.nihility.designpattern.decorator.Circle;
import cn.nihility.designpattern.decorator.Hero;
import cn.nihility.designpattern.decorator.Rectangle;
import cn.nihility.designpattern.decorator.RedShapeDecorator;
import cn.nihility.designpattern.decorator.Shape;
import cn.nihility.designpattern.decorator.SkillE;
import cn.nihility.designpattern.decorator.SkillQ;
import cn.nihility.designpattern.decorator.SkillR;
import cn.nihility.designpattern.decorator.SkillW;
import cn.nihility.designpattern.decorator.Skills;

public class DecoratorTest {

	@Test
	public void testDecorator() {

		Shape circle = new Circle();
		Shape redCircle = new RedShapeDecorator(new Circle());

		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("Circle with normal border");
		circle.draw();

		System.out.println("\nCircle of red border");
		redCircle.draw();

		System.out.println("\nRectangle of red border");
		redRectangle.draw();
	}

	@Test
	public void testHero() {
		Hero hero = new BlindMonk("英雄");

		Skills skills = new Skills(hero);
		Skills r = new SkillR(skills, "猛龙摆尾");
		Skills e = new SkillE(r, "天雷破/摧筋断骨");
		Skills w = new SkillW(e, "金钟罩/铁布衫");
		Skills q = new SkillQ(w, "天音波/回音击");
		// 学习技能
		q.learnSkills();
	}

}
