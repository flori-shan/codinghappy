package cn.nihility.spring.mvc.entity;

/**
 * 测试 Controller 数据传到 View 的实体类
 * @author muscari
 * @date 2019-06-05 10:28
 */
public class TransferUserDto {

    private String name;
    private Integer id;

    public TransferUserDto() { }

    public TransferUserDto(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TransferUserDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
