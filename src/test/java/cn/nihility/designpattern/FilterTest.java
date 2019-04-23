package cn.nihility.designpattern;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.nihility.designpattern.filter.AndCriteria;
import cn.nihility.designpattern.filter.Criteria;
import cn.nihility.designpattern.filter.CriteriaFemale;
import cn.nihility.designpattern.filter.CriteriaMale;
import cn.nihility.designpattern.filter.CriteriaSingle;
import cn.nihility.designpattern.filter.OrCriteria;
import cn.nihility.designpattern.filter.Person;

public class FilterTest {

	@Test
	public void testFilter() {
		List<Person> persons = new ArrayList<Person>();

		persons.add(new Person("Robert", "Male", "Single"));
		persons.add(new Person("John", "Male", "Married"));
		persons.add(new Person("Laura", "Female", "Married"));
		persons.add(new Person("Diana", "Female", "Single"));
		persons.add(new Person("Mike", "Male", "Single"));
		persons.add(new Person("Bobby", "Male", "Single"));

		Criteria male = new CriteriaMale();
		Criteria female = new CriteriaFemale();
		Criteria single = new CriteriaSingle();
		Criteria singleMale = new AndCriteria(single, male);
		Criteria singleOrFemale = new OrCriteria(single, female);

		System.out.println("Males: ");
		printPersons(male.meetCriteria(persons));

		System.out.println("\nFemales: ");
		printPersons(female.meetCriteria(persons));

		System.out.println("\nSingle Males: ");
		printPersons(singleMale.meetCriteria(persons));

		System.out.println("\nSingle Or Females: ");
		printPersons(singleOrFemale.meetCriteria(persons));
	}

	public static void printPersons(List<Person> persons) {
		for (Person person : persons) {
			System.out.println("Person : [ Name : " + person.getName() + ", Gender : " + person.getGender()
					+ ", Marital Status : " + person.getMaritalStatus() + " ]");
		}
	}

}
