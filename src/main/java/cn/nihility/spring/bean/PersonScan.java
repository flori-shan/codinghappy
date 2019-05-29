package cn.nihility.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author muscari
 * @date 2019-05-29 17:06
 */
public class PersonScan {
    private String name;
    private int age;

    @Autowired
    private FunctionBean bean;

    public PersonScan() {
    }

    public PersonScan(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void say() {
        bean.say(toString());
    }

    @Override
    public String toString() {
        return "PersonScan{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
