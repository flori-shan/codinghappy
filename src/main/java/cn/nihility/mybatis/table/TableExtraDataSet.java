package cn.nihility.mybatis.table;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muscari
 * @date 2019-06-19 14:38
 */
@Getter
@Setter
public class TableExtraDataSet {

    private String statisticInfo = "合计目标销售： ￥ 16982，增长 15%,合计OTB： ￥ 22298 k";
    private String saleTarget = "去年销售: ￥ 15003k，目标增长";
    private List<AdjustRatiosButton> adjustButtons = new ArrayList<>();

    public void addAdjustRationButton(AdjustRatiosButton adjustRatiosButton) {
        adjustButtons.add(adjustRatiosButton);
    }

}
