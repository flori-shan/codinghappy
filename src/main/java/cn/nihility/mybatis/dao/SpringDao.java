package cn.nihility.mybatis.dao;

import cn.nihility.mybatis.entity.InsertParamsEntity;
import cn.nihility.mybatis.entity.QueryParamsEntity;
import cn.nihility.mybatis.entity.Spring;
import cn.nihility.mybatis.entity.UpdateParamsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author muscari
 * @date 2019-06-19 16:11
 */
@Mapper
public interface SpringDao {
    List<Spring> getSprings();
    int insertData(Map<String, Object> data);

    List<Map<String, Object>> query(QueryParamsEntity query);
    int insert(InsertParamsEntity insert);
    int update(UpdateParamsEntity update);

}
