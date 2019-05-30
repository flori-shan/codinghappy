package cn.nihility.spring.datasource;

import cn.nihility.spring.jdbc.TestBean;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring Different DataSource Test
 * @author muscari
 * @date 2019-05-30 13:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-datasource.xml"})
public class DataSourceTest {

    @Resource
    private DataSource hikariDataSource;
    @Resource
    private DataSource comboPooledDataSource;
    @Resource
    private DataSource druidDataSource;
    @Resource
    private DataSource basicDataSource;

    @Resource
    private JdbcTemplate hikariJdbcTemplate;
    @Resource
    private JdbcTemplate comboJdbcTemplate;
    @Resource
    private JdbcTemplate druidJdbcTemplate;
    @Resource
    private JdbcTemplate basicJdbcTemplate;

    @After
    public void destroy() {
        hikariJdbcTemplate = null;
        comboJdbcTemplate = null;
        druidJdbcTemplate = null;
        basicJdbcTemplate = null;

        hikariDataSource = null;
        comboPooledDataSource = null;
        druidDataSource = null;
        basicDataSource = null;
    }

    @Test
    public void testConnection() {
        Assert.assertNotNull(hikariJdbcTemplate);
        Assert.assertNotNull(comboJdbcTemplate);
        Assert.assertNotNull(druidJdbcTemplate);
        Assert.assertNotNull(basicJdbcTemplate);

        System.out.println(hikariJdbcTemplate);
        System.out.println(comboJdbcTemplate);
        System.out.println(druidJdbcTemplate);
        System.out.println(basicJdbcTemplate);
    }

    @Test
    public void testConnQuery() {

        String query = "SELECT id, name FROM test WHERE id = ?";
        Object[] params = new Object[] {1};
        List<TestBean> beans = new ArrayList<>();

        jdbcTemplateQuery(query, params, hikariJdbcTemplate, "Hikari", beans);
        jdbcTemplateQuery(query, params, comboJdbcTemplate, "Combo", beans);
        jdbcTemplateQuery(query, params, druidJdbcTemplate, "Druid", beans);
        jdbcTemplateQuery(query, params, basicJdbcTemplate, "Basic", beans);

    }

    @Test
    public void testQueryResultList() {
        String query = "SELECT id, name FROM test";
        /*List<TestBean> beans = new ArrayList<>();
        String[] marks = new String[]{"Hikari", "Druid", "Combo", "Basic"};
        for (int i = 0; i < 4; i++) {
            beans.add(new TestBean(1, "测试", marks[i]));
            beans.add(new TestBean(2, "测试1", marks[i]));
            beans.add(new TestBean(3, "测试12", marks[i]));
            beans.add(new TestBean(4, "测试123", marks[i]));
        }*/

        List<TestBean> hikari = jdbcTemplateQuery(query, hikariJdbcTemplate, "Hikari");
        List<TestBean> druid = jdbcTemplateQuery(query, druidJdbcTemplate, "Druid");
        List<TestBean> combo = jdbcTemplateQuery(query, comboJdbcTemplate, "Combo");
        List<TestBean> basic = jdbcTemplateQuery(query, basicJdbcTemplate, "Basic");

        System.out.println(hikari);
        System.out.println(druid);
        System.out.println(combo);
        System.out.println(basic);
    }

    private void jdbcTemplateQuery(final String query, Object[] params,
                                   JdbcTemplate jdbcTemplate, final String remark, final List<TestBean> beans) {
        jdbcTemplate.query(query, params, (resultSet) -> {
            TestBean bean = new TestBean();
            bean.setId(resultSet.getInt("id"));
            bean.setName(resultSet.getString("name"));
            bean.setRemark(remark);
            beans.add(bean);
            System.out.println(remark + " bean : " + bean);
        });
    }

    private List<TestBean> jdbcTemplateQuery(final String query, JdbcTemplate jdbcTemplate, final String remark) {
        List<TestBean> listBeans = jdbcTemplate.query(query, (resultSet) -> {
            List<TestBean> beans = new ArrayList<>();
            TestBean bean;
            while (resultSet.next()) {
                bean = new TestBean();
                bean.setId(resultSet.getInt("id"));
                bean.setName(resultSet.getString("name"));
                bean.setRemark(remark);
                beans.add(bean);
                System.out.println(remark + " bean : " + bean);
            }
            return beans;
        });
        return listBeans;
    }

}
