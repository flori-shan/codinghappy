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
import cn.nihility.mybatis.beans.MybatisDept;
import cn.nihility.mybatis.beans.MybatisTestBean;
import cn.nihility.mybatis.dao.MybatisDeptMapper;

public class MybatisDeptTest {
	
	@Test
	public void testSelectDiscriminator() {
		
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();

			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			MybatisDeptMapper mapper = sqlSession.getMapper(MybatisDeptMapper.class);
			MybatisTestBean bean3 = mapper.getBeanByIdDiscriminator(100);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test bean3 = {}", bean3);
			
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
	public void testSelectCache() {
		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;

		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();

			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			MybatisDeptMapper mapper = sqlSession.getMapper(MybatisDeptMapper.class);
			MybatisDept bean = mapper.getDeptById(1);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get dept bean = {}", bean);
			
			MybatisTestBean bean1 = mapper.getBeanById(200);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test bean1 = {}", bean1);

			MybatisTestBean bean2 = mapper.getBeanAanDeptCascadeById(100);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test bean2 = {}", bean2);
			
			MybatisTestBean bean3 = mapper.getBeanByIdDiscriminator(100);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test bean3 = {}", bean3);
			
			bean = mapper.getDeptById(1);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get dept cached bean = {}", bean);
			
			bean1 = mapper.getBeanById(200);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test cached bean1 = {}", bean1);

			bean2 = mapper.getBeanAanDeptCascadeById(100);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test cached bean2 = {}", bean2);
			
			bean3 = mapper.getBeanByIdDiscriminator(100);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test cached bean3 = {}", bean3);
			
			
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

			MybatisDeptMapper mapper = sqlSession.getMapper(MybatisDeptMapper.class);
			MybatisDept bean = mapper.getDeptById(1);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get dept bean = {}", bean);
			
			MybatisTestBean bean1 = mapper.getBeanById(200);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test bean1 = {}", bean1);

			MybatisTestBean bean2 = mapper.getBeanAanDeptCascadeById(100);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test bean2 = {}", bean2);
			
			MybatisTestBean bean3 = mapper.getBeanByIdDiscriminator(100);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get test bean3 = {}", bean3);
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
	public void testComplexSelect() {

		InputStream inputStream = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;

		try {
			inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();

			LogbackUtil.loggerInfo(this.getClass(), "Mybatis sqlsession = {}", sqlSession);
			Assert.assertNotNull(sqlSession);

			MybatisDeptMapper mapper = sqlSession.getMapper(MybatisDeptMapper.class);
			
			/*MybatisTestBean bean1 = mapper.selectBeanAndDeptById(210);
			LoggerUtil.loggerInfo(this.getClass(), "Mybatis get test bean = {}", bean1);
			
			MybatisTestBean bean2 = mapper.selectBeanAndDeptById2(210);
			LoggerUtil.loggerInfo(this.getClass(), "Mybatis get test bean2 = {}", bean2);*/
			
			MybatisDept dept = mapper.selectDeptAndBeansByAge(20);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get dept bean = {}", dept);
			MybatisDept dept2 = mapper.selectDeptAndBeansByAge2(20);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis get dept2 bean = {}", dept2);

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

			MybatisDeptMapper mapper = sqlSession.getMapper(MybatisDeptMapper.class);
			
			String name = UUID.randomUUID().toString().substring(0, 8);
			MybatisDept bean = new MybatisDept();
			bean.setAge(27);
//			bean.setInfo(name+"部门-informations");
			bean.setDept(name+"部门");
			int count = mapper.insertMybatisDeptByBean(bean);
			LogbackUtil.loggerInfo(this.getClass(), "Mybatis insert dept count = {}, bean = {}", count, bean);
			
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
