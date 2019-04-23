package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.iterator.Iterator;
import cn.nihility.designpattern.iterator.NameRepository;

public class IteratorTest {

	@Test
	public void testIterator() {
		NameRepository namesRepository = new NameRepository();

		for (Iterator iter = namesRepository.getIterator(); iter.hasNext();) {
			String name = (String) iter.next();
			System.out.println("Name : " + name);
		}
	}

}
