package cn.nihility.spring.bean;

import java.util.Date;

/**
 * Spring bean Person entity.
 *
 * @author muscari
 * @date 2019-05-28 17:56
 */
public class Person {
    private String name;
    private String email;
    private int age;
    private Date birthDate;

    public Person() {
    }

    public Person(String name, String email, int age, Date birthDate) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                '}';
    }
}
