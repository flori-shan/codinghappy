package cn.nihility.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 基础的 Annotation Bean 注入，如：Component，Controller，Repository，Service
 * 注意： @Value 测试 .yml 失败, @Value 注解不支持 .yml
 * 1. application.properties 不支持中文， .yml 支持中文
 * @author muscari
 * @date 2019-06-12 14:05
 */
@Component
@ConfigurationProperties(prefix = "bean")
public class AnnotationBean {

    private String type = "Annotation Bean Name";
    /*@Value("${bean.no}")*/
    private String no;
    /*@Value("${bean.name}")*/
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String showName(String name) {
        return name + " : " + type;
    }

    @Override
    public String toString() {
        return "AnnotationBean{" +
                "type='" + type + '\'' +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
