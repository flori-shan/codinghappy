package cn.nihility.spring.mybatis.entity;

import java.io.Serializable;

/**
 * Spring bond mybatis' Entity Flower
 * @author muscari
 * @date 2019-06-01 00:08
 */
public class Flower implements Serializable {
    private static final long serialVersionUID = 6375495206023536456L;

    private Integer id;
    private String flowerName;
    private Integer flowerStar;
    private Integer flowerLife;

    public Flower() { }

    public Flower(Integer id, String flowerName, Integer flowerStar, Integer flowerLife) {
        this.id = id;
        this.flowerName = flowerName;
        this.flowerStar = flowerStar;
        this.flowerLife = flowerLife;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public void setFlowerStar(Integer flowerStar) {
        this.flowerStar = flowerStar;
    }

    public void setFlowerLife(Integer flowerLife) {
        this.flowerLife = flowerLife;
    }

    public Integer getId() {
        return id;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public Integer getFlowerStar() {
        return flowerStar;
    }

    public Integer getFlowerLife() {
        return flowerLife;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id='" + id + '\'' +
                ", flowerName='" + flowerName + '\'' +
                ", flowerStar='" + flowerStar + '\'' +
                ", flowerLife=" + flowerLife +
                '}';
    }
}
