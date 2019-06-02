package cn.nihility.spring.mybatis;

import cn.nihility.spring.mybatis.dao.FlowerDao;
import cn.nihility.spring.mybatis.entity.Flower;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Spring and Mybatis bound test
 * @author muscari
 * @date 2019-06-01 00:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-datasource.xml", "classpath:spring/spring-mybatis.xml"})
public class MybatisBondTest {

    @Autowired
    private FlowerDao flowerDao;

    @Test
    public void testGetFlowers() {
        List<Flower> flowers = flowerDao.getFlowers();
        Assert.assertNotNull(flowers);
        System.out.println(flowers);
    }

    @Test
    public void testGetFlowerById() {
        Flower flower = flowerDao.getFlowerById(1);
        Assert.assertNotNull(flower);
        System.out.println(flower);
    }

    @Test
    public void testGetFlowersByStar() {
        List<Flower> flowers = flowerDao.getFlowersByStar(9);
        Assert.assertNotNull(flowers);
        System.out.println(flowers);
    }

    @Test
    public void testInsertFlower() {
        Flower flower = new Flower();
//        flower.setFlowerLife(4);
        flower.setFlowerName("新水仙1");
        flower.setFlowerStar(10);

        int insert = flowerDao.insertFlowerByObject(flower);
        Assert.assertEquals(insert, 1);
        Assert.assertEquals(flower.getId(), (Integer)6);
        System.out.println("insert key " + flower.getId());
    }

    @Test
    public void testUpdate() {
        Flower flower = new Flower();
        flower.setId(6);
        flower.setFlowerLife(4);
//        flower.setFlowerName("新水仙1");
//        flower.setFlowerStar(10);

        int update = flowerDao.updateFlowerByObject(flower);
        Assert.assertEquals(update, 1);
    }

    @Test
    public void testDelete() {
        int del = flowerDao.deleteFlowerById(6);
        System.out.println("del cnt " + del);
        Assert.assertEquals(del, 1);
    }

}
