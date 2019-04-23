package cn.nihility.designpattern.singleton;

import java.util.ArrayList;

public class Multiton {
	
	private static ArrayList<Multiton> list = new ArrayList<>();
	private Multiton(int n) {
		for (int i = 0; i < n; i++) {
			list.add(new Multiton(i));
		}
	}

}
