package cn.nihility.mybatis.table;

import cn.nihility.mybatis.entity.QueryParamsEntity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muscari
 * @date 2019-06-21 10:09
 */
@Setter
@Getter
public class TableConfig {
    private List<Integer> editable;
    private List<String> rowCss;
    private List<String> rowFieldKeys;
    private JSONObject optionConfig;
    private QueryParamsEntity queryParamsEntity;

    public TableConfig() {
        editable = new ArrayList<>();
        rowCss = new ArrayList<>();
        rowFieldKeys = new ArrayList<>();
    }

    public void init() {
        JSONArray rowFieldKeysArray = JSONArray.parseArray("[\"spring#id\", \"spring#name\", \"spring#createdate\"]");
        JSONObject rowCssObj = JSONObject.parseObject("{\"spring#createdate\":\"white\"}");
        JSONArray rowEditableFieldKeys = JSONArray.parseArray("[\"spring#name\"]");

        JSONObject optionObj = JSONObject.parseObject("{\"fixedColumns\": 4, \"hide\": [\"spring#other\"], \"widths\": {\"spring#other\":100}, \"defaultWidth\": 80 }");

        for (int i = 0, ii = rowFieldKeysArray.size(); i < ii; i++) {
            String key = rowFieldKeysArray.getString(i);

            if (rowEditableFieldKeys.contains(key)) { editable.add(1); }
            else { editable.add(0); }

            if (rowCssObj.containsKey(key)) { rowCss.add(rowCssObj.getString(key)); }
            else { rowCss.add(""); }

            rowFieldKeys.add(key);
        }

        optionConfig = optionObj;

        queryParamsEntity = new QueryParamsEntity();

        for (String key : rowFieldKeys) {
            queryParamsEntity.addQueryFields(key.replace("#", ".") + " " + key);
        }
        queryParamsEntity.setTable("spring");

    }

}
