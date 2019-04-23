package cn.nihility.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import cn.nihility.mybatis.beans.MybatisTestBean;

public interface MybatisTestMapper {
	
	MybatisTestBean getBeanById(int id);
	
	List<MybatisTestBean> getBeansByName(String name);
	
	@MapKey("name")
	Map<String, MybatisTestBean> getBeansByParamMap(Map<String, Object> params);
	
	/* 返回 key:列名 值和 value: 列的值 */
	Map<String, Object> getBeanMapById(int id);
	
	/*
	 * 多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
	 * @MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
	 * */
	@MapKey("name")
	Map<String, MybatisTestBean> getBeanMapByName(String name);
	
	int insertMybatisBean(MybatisTestBean bean);
	
	int updateMybatisBeanAddressByNameAndAgeAndGender(@Param("name") String name, @Param("age") int age,
			@Param("gender") String gender, @Param("address") String address);
	
}
