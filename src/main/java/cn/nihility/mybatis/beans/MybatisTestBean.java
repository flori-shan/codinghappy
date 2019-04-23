package cn.nihility.mybatis.beans;

public class MybatisTestBean {

	private int id;
	private String name;
	private int age;
	private String email;
	private String gender;
	private String testAddress;
	private MybatisDept dept;

	public MybatisTestBean() {
	}

	public MybatisTestBean(int id, String name, int age, String email, String gender, String testAddress) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.gender = gender;
		this.testAddress = testAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTestAddress() {
		return testAddress;
	}

	public void setTestAddress(String testAddress) {
		this.testAddress = testAddress;
	}

	public MybatisDept getDept() {
		return dept;
	}

	public void setDept(MybatisDept dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MybatisTestBean [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append(", email=");
		builder.append(email);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", testAddress=");
		builder.append(testAddress);
		builder.append(", dept=");
		builder.append(dept);
		builder.append("]");
		return builder.toString();
	}

}
