package cn.nihility.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import cn.nihility.mvndrill.utils.LogbackUtil;
import cn.nihility.mybatis.beans.MybatisTestBean;
import cn.nihility.mybatis.dao.DynamicSqlMapper;

public class MybatisDynamicSqlTest {
	
	@Test
	public void testSelectIf() {
		
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();

			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
			MybatisTestBean bean = new MybatisTestBean();
//			bean.setAge(19);
			bean.setGender("M");
			bean.setName("5a%");
			List<MybatisTestBean> beans = mapper.selectBeanByBeanUseIf(bean);
			
//			List<MybatisTestBean> beans = mapper.selectBeanByBeanUseSuffix(bean);
			
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis select if beans = {}", beans);
			
		} catch (Exception ex) {
			LogbackUtil.loggerError(this.getClass(), "Mybatis execute error, msg = {}", ex);
		} finally {
			try {
				if (null != sqlSession) {
					sqlSession.close();
				}
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (IOException e) {
				LogbackUtil.loggerError(this.getClass(), "Mybatis close resources error, msg = {}", e);
			}
		}
		
	}
	
	@Test
	public void testSelectForeach() {
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();

			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
			List<Integer> listids = new ArrayList<>();
			listids.add(300);
			listids.add(400);
			listids.add(301);
			listids.add(302);
			listids.add(305);
			List<MybatisTestBean> beans = mapper.selectBeanByIdsUseForeach(listids);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis select foreach beans = {}", beans);
			
		} catch (Exception ex) {
			LogbackUtil.loggerError(this.getClass(), "Mybatis execute error, msg = {}", ex);
		} finally {
			try {
				if (null != sqlSession) {
					sqlSession.close();
				}
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (IOException e) {
				LogbackUtil.loggerError(this.getClass(), "Mybatis close resources error, msg = {}", e);
			}
		}
	}
	
	@Test
	public void testUpdateSet() {
		
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();

			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
			MybatisTestBean bean = new MybatisTestBean();
//			bean.setAge(19);
			bean.setId(300);
			bean.setEmail("mybatisupdate@mybatis.com");
			bean.setTestAddress("mybatistestAddress");
//			List<MybatisTestBean> beans = mapper.selectBeanByBeanUseIf(bean);
			int count = mapper.updateMybatisTestSet(bean);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis update set count = {}, bean = {}", count, bean);
			
			sqlSession.commit();
			
		} catch (Exception ex) {
			LogbackUtil.loggerError(this.getClass(), "Mybatis execute error, msg = {}", ex);
		} finally {
			try {
				if (null != sqlSession) {
					sqlSession.close();
				}
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (IOException e) {
				LogbackUtil.loggerError(this.getClass(), "Mybatis close resources error, msg = {}", e);
			}
		}
		
	}

}
