package cn.nihility.designpattern.filter;

import java.util.List;

public class OrCriteria implements Criteria {

	private Criteria criteria;
	private Criteria otherCriteria;

	public OrCriteria(Criteria criteria, Criteria otherCriteria) {
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
	}

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> firstCriteria = criteria.meetCriteria(persons);
		List<Person> secondCriteria = otherCriteria.meetCriteria(persons);

		for (Person person : firstCriteria) {
			if (!secondCriteria.contains(person)) {
				secondCriteria.add(person);
			}
		}
		
		return secondCriteria;
	}

}
