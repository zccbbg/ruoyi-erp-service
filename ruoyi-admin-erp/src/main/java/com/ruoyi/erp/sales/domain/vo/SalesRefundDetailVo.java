package com.ruoyi.erp.sales.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseRefundDetailVo;
import com.ruoyi.erp.sales.domain.entity.SalesRefundDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

/**
 * 销售退货单明细视图对象 Sales_refund_detail
 *
 * @date 2025-03-12
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SalesRefundDetail.class)
public class SalesRefundDetailVo extends BaseRefundDetailVo {


}
