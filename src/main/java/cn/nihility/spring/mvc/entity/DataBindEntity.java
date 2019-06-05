package cn.nihility.spring.mvc.entity;

import java.util.Date;

/**
 * Spring MVC 数据绑定实体类,都是 Object 对象
 * @author muscari
 * @date 2019-06-05 14:25
 */
public class DataBindEntity {

    private Integer id;
    private String name;
    private Date birthDate;

    private DataBindChildEntity info;

    public DataBindEntity() { }

    public DataBindEntity(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public DataBindEntity(Integer id, String name, Date birthDate, DataBindChildEntity info) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.info = info;
    }

    public DataBindChildEntity getInfo() {
        return info;
    }

    public void setInfo(DataBindChildEntity info) {
        this.info = info;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "DataBindEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", info=" + info +
                '}';
    }
}
