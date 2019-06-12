package cn.nihility.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * .yml 复杂的 Bean 属性注入值
 * @author muscari
 * @date 2019-06-12 17:31
 */
@Component
/* @PropertySource() 指定加载配置文件,但是仅仅支持 properties 配置文件 */
@ConfigurationProperties(prefix = "entity")
public class ComplexInjectBean {

    /* @Value("${}") 不能够自动注入值 */
//    @Value("${entity.simpleTypeProp}")
    private String simpleTypeProp;
//    @Value("${entity.arrayTypeProp}")
    private String[] arrayTypeProp;
//    @Value("${entity.listTypeProp}")
    private List<String> listTypeProp;
//    @Value("${entity.mapTypeProp}")
    private Map<String, String> mapTypeProp;
//    @Value("${entity.listMapProp}")
    private List<Map<String, String>> listMapProp;
//    @Value("${entity.jsonProp}")
    private ComplexInnerClass jsonProp;

    public String getSimpleTypeProp() {
        return simpleTypeProp;
    }

    public void setSimpleTypeProp(String simpleTypeProp) {
        this.simpleTypeProp = simpleTypeProp;
    }

    public String[] getArrayTypeProp() {
        return arrayTypeProp;
    }

    public void setArrayTypeProp(String[] arrayTypeProp) {
        this.arrayTypeProp = arrayTypeProp;
    }

    public List<String> getListTypeProp() {
        return listTypeProp;
    }

    public void setListTypeProp(List<String> listTypeProp) {
        this.listTypeProp = listTypeProp;
    }

    public Map<String, String> getMapTypeProp() {
        return mapTypeProp;
    }

    public void setMapTypeProp(Map<String, String> mapTypeProp) {
        this.mapTypeProp = mapTypeProp;
    }

    public List<Map<String, String>> getListMapProp() {
        return listMapProp;
    }

    public void setListMapProp(List<Map<String, String>> listMapProp) {
        this.listMapProp = listMapProp;
    }

    public ComplexInnerClass getJsonProp() {
        return jsonProp;
    }

    public void setJsonProp(ComplexInnerClass jsonProp) {
        this.jsonProp = jsonProp;
    }

    @Override
    public String toString() {
        return "ComplexInjectBean{" +
                "simpleTypeProp='" + simpleTypeProp + '\'' +
                ", arrayTypeProp=" + Arrays.toString(arrayTypeProp) +
                ", listTypeProp=" + listTypeProp +
                ", mapTypeProp=" + mapTypeProp +
                ", listMapProp=" + listMapProp +
                ", jsonProp=" + jsonProp +
                '}';
    }

    static class ComplexInnerClass {
        private Integer id;
        private String name;
        private Integer age;

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

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "ComplexInnerClass{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
