package cn.nihility.spring.mybatis.dao;

import cn.nihility.spring.mybatis.entity.Flower;

import java.util.List;

/**
 * Spring Mybatis Entity Flower Mapper Interface
 * @author muscari
 * @date 2019-06-01 00:12
 */
public interface FlowerDao {

    List<Flower> getFlowers();

    List<Flower> getFlowersByStar(int star);

    Flower getFlowerById(int id);

    int insertFlowerByObject(Flower flower);

    int deleteFlowerById(int id);

    int updateFlowerByObject(Flower flower);

}
