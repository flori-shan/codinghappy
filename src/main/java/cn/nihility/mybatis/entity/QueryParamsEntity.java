package cn.nihility.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 条件查询参数的实体类，封装各种查询条件
 * @author muscari
 * @date 2019-06-19 17:50
 */
@Getter
@Setter
public class QueryParamsEntity {

    /* 查询的字段名称， 如： query_value 或者 query_table.query_value alias_name */
    private List<String> queryFields = new ArrayList<>();
    /*
    * 查询中的 WHERE 的选择条件，筛选的字段和对应的值， 使用 prepareStatement 中 ? 参数的形式
    * 如： query_field: query_value 或者 table_alias.query_field : query_value
    * */
    private Map<String, String> queryFieldsAndValues = new HashMap<>();
    /* 和 queryStaticParams 同样，但是此是条件的一部分，由系统拼接 */
    private List<String> queryStaticParamsList = new ArrayList<>();
    /*
    * 查询当中 WHERE 的表字段连接或者写死的筛选条件：
    * 如: table_field_x = table_field_y and table_filed_z = table_field_x
    * 或者： table_alias_x.query_field_x = table_alias_y.query_field_x
    * 或：table_alias_x.query_field_x = 'query_filter_value'
    * */
    private String queryStaticParams;
    /*
    * 要查的表，可以写单表或多表
    * 如： table_x
    * 或者： table_x, table_y, table_z
    * 或者： table_x table_alias_x, table_y table_alias_y, table_z table_alias_z
    * */
    private String table;

    public void addQueryFields(@NotNull String field) {
        queryFields.add(field);
    }

    public void addQueryFieldsAndValues(@NotNull String field, @NotNull String value) {
        queryFieldsAndValues.put(field, value);
    }

    public void addQueryStaticParamsList(@NotNull String staticQuery) {
        queryStaticParamsList.add(staticQuery);
    }

}
