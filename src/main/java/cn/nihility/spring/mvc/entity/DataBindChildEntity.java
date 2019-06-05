package cn.nihility.spring.mvc.entity;

/**
 * Spring MVC 数据绑定的拓展属性对象
 * @author muscari
 * @date 2019-06-05 16:39
 */
public class DataBindChildEntity {

    private Integer id;
    private String tel;
    private String address;

    public DataBindChildEntity() { }

    public DataBindChildEntity(Integer id, String tel, String address) {
        this.id = id;
        this.tel = tel;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DataBindChildEntity{" +
                "id=" + id +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
