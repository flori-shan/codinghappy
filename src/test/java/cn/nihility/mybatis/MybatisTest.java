package cn.nihility.mybatis;

import cn.nihility.mybatis.dao.SpringDao;
import cn.nihility.mybatis.entity.InsertParamsEntity;
import cn.nihility.mybatis.entity.QueryParamsEntity;
import cn.nihility.mybatis.entity.Spring;
import cn.nihility.mybatis.entity.UpdateParamsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author muscari
 * @date 2019-06-19 16:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private SpringDao springDao;

    @Test
    public void test() {
        System.out.println(springDao);
        List<Spring> springs = springDao.getSprings();
        System.out.println(springs);
    }

    @Test
    public void testInsert() {
        Map<String, Object> params = new HashMap<>();
        List<String> items = new ArrayList<>();
        items.add("id");
        items.add("name");

        List<String> values = new ArrayList<>();
        values.add("12");
        values.add("add Name");

        params.put("table", "spring");
        params.put("items", items);
        params.put("values", values);

        params.put("id", 11);
        params.put("name", "Insert VName");

        int i = springDao.insertData(params);
        System.out.println(i);

    }

    @Test
    public void testQuery() {
        QueryParamsEntity queryParamsEntity = new QueryParamsEntity();
        queryParamsEntity.addQueryFields("id");
        queryParamsEntity.addQueryFields("name");
        queryParamsEntity.addQueryFields("createdate");

        queryParamsEntity.setTable("spring");
        queryParamsEntity.addQueryFieldsAndValues("id", "12");
        queryParamsEntity.addQueryFieldsAndValues("name", "add Name");

        List<Map<String, Object>> query = springDao.query(queryParamsEntity);
        System.out.println(query);

    }

    @Test
    public void testQuery01() {
        QueryParamsEntity queryParamsEntity = new QueryParamsEntity();
        queryParamsEntity.addQueryFields("s1.id spring_id");
        queryParamsEntity.addQueryFields("s1.name spring_name");
        queryParamsEntity.addQueryFields("s1.createdate spring_createdate");
        queryParamsEntity.addQueryFields("s2.id spring2_id");
        queryParamsEntity.addQueryFields("s2.name spring2_name");
        queryParamsEntity.addQueryFields("s2.createdate spring2_createdate");

        queryParamsEntity.setTable("spring s1, spring01 s2");

        queryParamsEntity.addQueryStaticParamsList("s1.id = s2.id");

        queryParamsEntity.setQueryStaticParams("s1.id = 10");

        queryParamsEntity.addQueryFieldsAndValues("s1.id", "10");

        List<Map<String, Object>> query = springDao.query(queryParamsEntity);
        System.out.println(query);

    }

    @Test
    public void testUpdate() {
        UpdateParamsEntity updateParamsEntity = new UpdateParamsEntity();

        updateParamsEntity.setTable("spring s1");

        updateParamsEntity.setUpdateStaticFieldSet("s1.createdate = sysdate");

        updateParamsEntity.addUpdateFieldsAndValues("s1.name", "new spring 新名称");

        updateParamsEntity.addWhereItems("s1.id", "10");

        int cnt = springDao.update(updateParamsEntity);
        System.out.println("update cnt = " + cnt);
    }

    @Test
    public void testInsert01() {
        InsertParamsEntity insertParamsEntity = new InsertParamsEntity();
        insertParamsEntity.addInsertFields("id");
        insertParamsEntity.addInsertFields("name");

        insertParamsEntity.addInsertVables("21");
        insertParamsEntity.addInsertVables("Insert Field 21 名称");

//        insertParamsEntity.addInsertStaticFields("createdate");
//        insertParamsEntity.addInsertStaticValues("sysdate");

        insertParamsEntity.setTable("spring");

        int insert = springDao.insert(insertParamsEntity);
        System.out.println("Insert cnt " + insert);

    }

}
