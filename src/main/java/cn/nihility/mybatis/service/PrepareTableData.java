package cn.nihility.mybatis.service;

import cn.nihility.mybatis.dao.SpringDao;
import cn.nihility.mybatis.entity.QueryParamsEntity;
import cn.nihility.mybatis.table.*;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yzx
 * @date 2019-06-21 09:50
 */
@Service
public class PrepareTableData {

    @Autowired
    private SpringDao springDao;

    private TableConfig tableConfig;

    public TableDataSet getTableData() {

        initPrepareValues();

        TableDataSet tableDataSet = new TableDataSet();

        tableDataSet.setThead(getTableHead());
        tableDataSet.setTbody(getTableBody());
        tableDataSet.setOption(tableConfig.getOptionConfig());
        tableDataSet.setExtra(getTableExtra());

        return tableDataSet;
    }

    private TableExtraDataSet getTableExtra() {
        TableExtraDataSet extraDataSet = new TableExtraDataSet();

        AdjustRatiosButton button;

        for (int i = 0; i < 4; i++) {
            button = new AdjustRatiosButton();

            button.setValue("89");
            button.setDefaultValue(String.valueOf(i * 10));
            button.setDesc("测试 Desc : " + i);
            button.setMark("%");
            button.setTable("spring");

            extraDataSet.addAdjustRationButton(button);
        }

        return extraDataSet;
    }

    private void initPrepareValues() {
        tableConfig = new TableConfig();
        tableConfig.init();
    }

    private List<TableRowDataSet> getTableBody() {
        List<Map<String, Object>> queryResult = springDao.query(tableConfig.getQueryParamsEntity());
        System.out.println(queryResult);
        TableRowDataSet tableRowDataSet;
        List<TableRowDataSet> tableRowList = new ArrayList<>();

        for (Map<String, Object> row : queryResult) {
            tableRowDataSet = new TableRowDataSet();

            tableRowDataSet.setRowObj(row.get("sprint#id"));
            tableRowDataSet.setValues(row);
            tableRowDataSet.setEditable(tableConfig.getEditable());
            tableRowDataSet.setCss(tableConfig.getRowCss());

            tableRowList.add(tableRowDataSet);
        }

        return tableRowList;
    }

    private QueryParamsEntity getQueryParamsEntity() {
        QueryParamsEntity queryParamsEntity = new QueryParamsEntity();
        queryParamsEntity.addQueryFields("s1.id spring#id");
        queryParamsEntity.addQueryFields("s1.name spring#name");
        queryParamsEntity.addQueryFields("s1.createdate spring#createdate");

        queryParamsEntity.setTable("spring s1");

        return queryParamsEntity;
    }

    private JSONArray getTableHead() {
        return JSONArray.parseArray("[{\"headerName\":\"测试表头\",\"children\":[{\"headerName\":\"Spring_Id\",\"field\":\"spring#id\"},{\"headerName\":\"Spring_Name\",\"field\":\"spring#name\"},{\"headerName\":\"Spring_CreateDate\",\"field\":\"spring#createdate\"}]}]");
    }


}
