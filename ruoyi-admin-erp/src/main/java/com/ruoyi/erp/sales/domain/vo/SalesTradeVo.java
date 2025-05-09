package com.ruoyi.erp.sales.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseTradeVo;
import com.ruoyi.erp.sales.domain.entity.SalesTrade;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.util.List;

/**
 * 销售出库单视图对象 Sales_trade
 *
 * @date 2025-03-12
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SalesTrade.class)
public class SalesTradeVo <T extends SalesTradeDetailVo> extends BaseTradeVo<T> {
    private List<String> refundNoList;
}
