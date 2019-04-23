package cn.nihility.mybatis.beans;

import java.util.List;

public class MybatisDept {

	private int id;
	private String info;
	private String dept;
	private int age;
	private List<MybatisTestBean> beans;

	public MybatisDept() {
	}

	public MybatisDept(int id, String info, String dept, int age) {
		this.id = id;
		this.info = info;
		this.dept = dept;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<MybatisTestBean> getBeans() {
		return beans;
	}

	public void setBeans(List<MybatisTestBean> beans) {
		this.beans = beans;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MybatisDept [id=");
		builder.append(id);
		builder.append(", info=");
		builder.append(info);
		builder.append(", dept=");
		builder.append(dept);
		builder.append(", age=");
		builder.append(age);
		builder.append(", beans=");
		builder.append(beans);
		builder.append("]");
		return builder.toString();
	}

}
