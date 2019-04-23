package cn.nihility.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import cn.nihility.bean.EmpBean;

public interface EmpMapper {
	
	EmpBean selectEmpByEmpno(Integer empno);
	
	List<EmpBean> selectEmpsByDeptno(Integer deptno);
	
	@MapKey("empno")
	Map<Integer, EmpBean> selectEmpsMapByDeptno(Integer deptno);

}
