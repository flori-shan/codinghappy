package cn.nihility.mybatis.table;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author muscari
 * @date 2019-06-19 14:35
 */
@Getter
@Setter
public class TableOptionDataSet {
    private Integer fixedColumns = 1;
    private List<String> hide = new ArrayList<>();
    private Map<String, Integer> widths = new HashMap<>();
    private Integer defaultWidth = 80;
}
