package cn.nihility.designpattern.builder;

import java.util.ArrayList;

public class Meal {

	private ArrayList<Item> items = new ArrayList<>();

	public void addItem(Item item) {
		items.add(item);
	}

	public float getCost() {
		float cost = 0.0F;
		for (Item it : items) {
			cost += it.price();
		}
		return cost;
	}

	public void showItems() {
		for (Item item : items) {
			System.out.println("Item : " + item.name() + " , Price: " + item.price() + ", Wrapper: " + item.packing().pack());
		}
	}

}
