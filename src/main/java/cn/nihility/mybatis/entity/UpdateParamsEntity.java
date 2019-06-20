package cn.nihility.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 条件更新表的参数实体类，封装各种更新字段值和更新匹配查询条件
 * @author muscari
 * @date 2019-06-19 17:54
 */
@Getter
@Setter
public class UpdateParamsEntity {

    /*
    * 要更新的字段和对应的字段值
    * 如： update_field: update_value
    * 或者： table_alias.update_field : update_value
    * */
    private Map<String, String> updateFieldsAndValues = new HashMap<>();

    /*
    * 写好的要更新的字段值
    * 如： update_table.create_data = sysdate
    * */
    private String updateStaticFieldSet;
    /*
    * 要更新的值的筛选条件，或当作 WHERE 之后的条件
    * 如： update_filter_field: field_value
    * 或者： table_alias.update_filter_field: field_value
    * */
    private Map<String, String> whereItems = new HashMap<>();
    /*
    * 更新的写死的更新条件, 会拼接在 WHERE 之后
    * 如： filter_field = 'filter_value'
    * 或者 filter_field = (select field form table ...)
    * */
    private String whereStaticValue;
    /*
    * 要更新的表名称：如：update_table 或者 update_table update_table_alias
    * */
    private String table;

    public void addUpdateFieldsAndValues(@NotNull String field, @NotNull String value) {
        updateFieldsAndValues.put(field, value);
    }

    public void addWhereItems(@NotNull String key, @NotNull String value) {
        whereItems.put(key, value);
    }
}
