package com.ruoyi.erp.sales.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseTradeDetailVo;
import com.ruoyi.erp.sales.domain.entity.SalesTradeDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

/**
 * 销售出库单明细视图对象 Sales_trade_detail
 *
 *
 * @date 2025-03-12
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SalesTradeDetail.class)
public class SalesTradeDetailVo extends BaseTradeDetailVo {
}
