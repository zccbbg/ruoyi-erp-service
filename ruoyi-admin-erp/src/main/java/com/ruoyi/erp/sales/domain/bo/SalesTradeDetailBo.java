package com.ruoyi.erp.sales.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseTradeDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesTradeDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 销售出库单明细业务对象 sales_trade_detail
 *
 *
 * @date 2025-03-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SalesTradeDetail.class, reverseConvertGenerate = false)
public class SalesTradeDetailBo extends BaseTradeDetailBo {
}
