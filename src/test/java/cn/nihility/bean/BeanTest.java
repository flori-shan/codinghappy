package cn.nihility.bean;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Boot Bean Test
 * @author muscari
 * @date 2019-06-12 14:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTest {

    @Autowired
    private AnnotationBean annotationBean;

    @Autowired
    private ComplexInjectBean complexInjectBean;

    @Test
    public void testAnnotationBean() {
        Assert.assertEquals("Hello Spring Boot : Annotation Bean Name", annotationBean.showName("Hello Spring Boot"));
        System.out.println(annotationBean);
    }

    @Test
    public void testComplexInjection() {
        Assert.assertNotNull(complexInjectBean);
        System.out.println(complexInjectBean);
    }

}
