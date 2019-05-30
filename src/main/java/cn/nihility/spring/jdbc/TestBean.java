package cn.nihility.spring.jdbc;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * JDBC Test Bean
 * @author muscari
 * @date 2019-05-30 14:45
 */
public class TestBean {
    private Integer id;
    private String name;
    private String remark;

    public TestBean() { }

    public TestBean(Integer id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TestBean testBean = (TestBean) o;

        return new EqualsBuilder()
                .append(id, testBean.id)
                .append(name, testBean.name)
                .append(remark, testBean.remark)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(remark)
                .toHashCode();
    }
}
