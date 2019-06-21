package cn.nihility.mybatis.table;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author muscari
 * @date 2019-06-19 11:58
 */
@Getter
@Setter
public class TableDataSet {

    private JSONArray thead;
    private List<TableRowDataSet> tbody;
    private JSONObject option;
    private TableExtraDataSet extra;

    public String toJSONObject() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
    }

}
