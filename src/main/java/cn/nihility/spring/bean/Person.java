package cn.nihility.spring.bean;

import java.util.Date;

/**
 * Spring Bean
 * @author muscari
 * @date 2019-05-29 00:08
 */
public class Person {

    private String name;
    private String email;
    private int age;
    private Date birthDate;

    public Person() { }

    public Person(String name, String email, int age, Date date) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthDate = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
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
                ", date=" + birthDate +
                '}';
    }
}
