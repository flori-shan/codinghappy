package cn.nihility.bean;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * Bean 生成测试
 * @author muscari
 * @date 2019-06-13 00:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanGenerateTest {

    @Autowired
    @Qualifier("personTalkingImpl")
    private Talking personTalking;

    @Resource
    private Talking dogTalkingImpl;

    @Test
    public void testBeanConfig() {
        Assert.assertEquals("Person saying : Hai, Person!", personTalking.say("Hai, Person!"));
        Assert.assertEquals("Dog saying : Hai, Dog!", dogTalkingImpl.say("Hai, Dog!"));
    }

}
