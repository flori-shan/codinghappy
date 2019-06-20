package cn.nihility.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muscari
 * @date 2019-06-19 18:05
 */
@Getter
@Setter
public class InsertParamsEntity {

    private List<String> insertFields = new ArrayList<>();
    private List<String> insertValues = new ArrayList<>();
    private List<String> insertStaticFields = new ArrayList<>();
    private List<String> insertStaticValues = new ArrayList<>();
    private String table;

    public void addInsertFields(@NotNull String field) {
        insertFields.add(field);
    }

    public void addInsertVables(@NotNull String value) {
        insertValues.add(value);
    }

    public void addInsertStaticFields(@NotNull String staticField) {
        insertStaticFields.add(staticField);
    }

    public void addInsertStaticValues(@NotNull String staticValue) {
        insertStaticValues.add(staticValue);
    }

}
