package cn.nihility.mybatis.table;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author muscari
 * @date 2019-06-19 11:59
 */
@Getter
@Setter
public class TableRowDataSet {

    private Object rowObj;
    private Map<String, Object> values;
    private List<String> css;
    private List<Integer> editable;

    public TableRowDataSet() {
        values = new HashMap<>();
        css = new ArrayList<>();
        editable = new ArrayList<>();
    }

    public TableRowDataSet(int initCapacity) {
        values = new HashMap<>(initCapacity);
        css = new ArrayList<>(initCapacity);
        editable = new ArrayList<>(initCapacity);
    }

    public TableRowDataSet(String rowObj, int initCapacity) {
        this.rowObj = rowObj;
        this.values = new HashMap<>(initCapacity);
        this.css = new ArrayList<>(initCapacity);
        this.editable = new ArrayList<>(initCapacity);
    }

    public JSONObject toJSONObject() {
        return (JSONObject) JSON.toJSON(this);
    }
}
