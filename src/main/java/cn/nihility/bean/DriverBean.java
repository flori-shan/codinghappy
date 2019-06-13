package cn.nihility.bean;

/**
 * Driver Class
 * @author muscari
 * @date 2019-06-13 13:55
 */
public class DriverBean {
    private Integer id;
    private String name;
    private CarBean car;

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

    public CarBean getCar() {
        return car;
    }

    public void setCar(CarBean car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "DriverBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", CarBean='" + car + '\'' +
                '}' + " : " + super.toString();
    }
}
