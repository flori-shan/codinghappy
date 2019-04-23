package cn.nihility.mybatis.dao;

import cn.nihility.mybatis.beans.MybatisDept;
import cn.nihility.mybatis.beans.MybatisTestBean;

public interface MybatisDeptMapper {

	MybatisDept getDeptById(int id);
	
	MybatisTestBean getBeanById(int id);
	MybatisTestBean getBeanAanDeptCascadeById(int id);
	MybatisTestBean getBeanByIdDiscriminator(int id);
	
	MybatisTestBean selectBeanAndDeptById(int id);
	MybatisTestBean selectBeanAndDeptById2(int id);
	
	MybatisDept selectDeptAndBeansByAge(int age);
	MybatisDept selectDeptAndBeansByAge2(int age);
	
	int insertMybatisDeptByBean(MybatisDept bean);
	
}
