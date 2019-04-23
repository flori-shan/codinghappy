package cn.nihility.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import cn.nihility.bean.EmpBean;
import cn.nihility.dao.EmpMapper;

public class SqlSessionTest {
	
	@Test
	public void testSqlSession() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-conf.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		System.out.println(sqlSession);
		Assert.assertNotNull(sqlSession);
		
		try {
			EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
			EmpBean eb = empMapper.selectEmpByEmpno(7369);
			System.out.println(eb);
			
			List<EmpBean> le = empMapper.selectEmpsByDeptno(10);
			System.out.println(le);
			
			Map<Integer, EmpBean> ep = empMapper.selectEmpsMapByDeptno(10);
			System.out.println(ep);
			
		} finally {
			sqlSession.close();
		}
		
		
	}

}
