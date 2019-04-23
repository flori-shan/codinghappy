package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.builder.ConcreteBuilder1;
import cn.nihility.designpattern.builder.Director;
import cn.nihility.designpattern.builder.Meal;
import cn.nihility.designpattern.builder.MealBuilder;

public class BuilderTest {

	@Test
	public void testBuilder() {

		MealBuilder mealBuilder = new MealBuilder();
		Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
		System.out.println("NonVegMeal cost: " + nonVegMeal.getCost());
		System.out.println("NonVegMeal items: ");
		nonVegMeal.showItems();

		System.out.println("-------------------------------");

		Meal vegMeal = mealBuilder.prepareVegMeal();
		System.out.println("VegMeal cost: " + vegMeal.getCost());
		System.out.println("VegMeal itemes:");
		vegMeal.showItems();

	}
	
	@Test
	public void testBuilderConcreteBuilder() {
		/* 建造者 */
		ConcreteBuilder1 concreteBuilder1 = new ConcreteBuilder1();
		/* 监工者 */
		Director director = new Director(concreteBuilder1);
		director.construct();
		
		System.out.println("result : " + concreteBuilder1.result());
	}

}
