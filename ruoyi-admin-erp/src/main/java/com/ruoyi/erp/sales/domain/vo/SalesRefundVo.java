package com.ruoyi.erp.sales.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseRefundVo;
import com.ruoyi.erp.sales.domain.entity.SalesRefund;
import com.ruoyi.erp.sales.domain.vo.SalesRefundDetailVo;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

/**
 * 销售退货单视图对象 Sales_refund
 *
 * @date 2025-03-12
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SalesRefund.class)
public class SalesRefundVo<T extends SalesRefundDetailVo> extends BaseRefundVo<T> {
}
