package cn.nihility.mybatis.dao;

import java.util.List;

import cn.nihility.mybatis.beans.MybatisTestBean;

public interface DynamicSqlMapper {
	
	List<MybatisTestBean> selectBeanByBeanUseIf(MybatisTestBean bean);
	List<MybatisTestBean> selectBeanByBeanUseSuffix(MybatisTestBean bean);
	
	List<MybatisTestBean> selectBeanByIdsUseForeach(List<Integer> ids);
	
	int updateMybatisTestSet(MybatisTestBean bean);

}
