package cn.nihility.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import cn.nihility.mvndrill.utils.LogbackUtil;
import cn.nihility.mybatis.beans.MybatisTestBean;
import cn.nihility.mybatis.dao.MybatisTestMapper;

public class MybatisTest {

	@Test
	public void testInitialize() {

		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;

		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();

			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			MybatisTestMapper mapper = sqlSession.getMapper(MybatisTestMapper.class);
			MybatisTestBean bean = mapper.getBeanById(200);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get bean = {}", bean);

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
	public void testSelect() {
		
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;

		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();
			
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			MybatisTestMapper mapper = sqlSession.getMapper(MybatisTestMapper.class);
			
			/* Select list beans */
//			List<MybatisTestBean> beans = mapper.getBeansByName("%my%");
//			LoggerUtil.loggerInfo(this.getClass(), "Mybatis select list beans count = {}, info = {}", beans.size(), beans);
			
//			Map<String, Object> objmap = mapper.getBeanMapById(300);
//			LoggerUtil.loggerInfo(this.getClass(), "Mybatis select map beans count = {}, info = {}", objmap.size(), objmap);
			
//			Map<String, MybatisTestBean> objmap = mapper.getBeanMapByName("%my%");
//			LoggerUtil.loggerInfo(this.getClass(), "Mybatis select map beans count = {}, info = {}", objmap.size(), objmap);
			
//			Map<String, Object> param = new HashMap<>();
//			param.put("name", "%cf%");
//			param.put("gender", "F");
//			param.put("age", 18);
//			Map<String, MybatisTestBean> objmap = mapper.getBeansByParamMap(param);
//			LoggerUtil.loggerInfo(this.getClass(), "Mybatis select map beans count = {}, info = {}", objmap.size(), objmap);

			
			mapper.updateMybatisBeanAddressByNameAndAgeAndGender("%5a", 18, "F", "update-address");
			
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
	public void testInsert() {
		
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;

		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();
			
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			MybatisTestMapper mapper = sqlSession.getMapper(MybatisTestMapper.class);
			
			String name = UUID.randomUUID().toString().substring(0, 8);
			MybatisTestBean bean = getBean(20, "F", name, name+"@mybatis.cn", name+"地址");
			int insertCount = mapper.insertMybatisBean(bean);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis insert bean count = {}", insertCount);

			sqlSession.commit();
		} catch (Exception ex) {
			if (null != sqlSession) {
				sqlSession.rollback();
			}
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
	public void testUpdate() {
		
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;

		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();
			
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			MybatisTestMapper mapper = sqlSession.getMapper(MybatisTestMapper.class);
			int updateCount = mapper.updateMybatisBeanAddressByNameAndAgeAndGender("5a%", 18, "F", "update-address");
			LogbackUtil.loggerInfo(this.getClass(), "update count = {}", updateCount);
			
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
	
	private MybatisTestBean getBean(int age, String gender, String name, String email, String address) {
		MybatisTestBean bean = new MybatisTestBean();
		bean.setAge(age);
		bean.setTestAddress(address);
		bean.setName(name);
		bean.setGender(gender);
		bean.setEmail(email);
		return bean;
	}

}
