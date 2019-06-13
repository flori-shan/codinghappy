package cn.nihility.bean;

/**
 * Car Bean Class
 * @author muscari
 * @date 2019-06-13 13:56
 */
public class CarBean {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}' + " : " + super.toString();
    }
}
