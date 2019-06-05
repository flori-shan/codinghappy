package cn.nihility.spring.mvc.entity;

import java.util.List;

/**
 * @author muscari
 * @date 2019-06-05 17:18
 */
public class DataBindChildEntityListDto {
    private List<DataBindChildEntity> entities;

    public DataBindChildEntityListDto() { }

    public DataBindChildEntityListDto(List<DataBindChildEntity> entities) {
        this.entities = entities;
    }

    public List<DataBindChildEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<DataBindChildEntity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "DataBindChildEntityListDto{" +
                "entities=" + entities +
                '}';
    }
}
