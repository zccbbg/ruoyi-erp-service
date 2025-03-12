package com.ruoyi.erp.sales.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseRefundBo;
import com.ruoyi.erp.sales.domain.bo.SalesRefundDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesRefund;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 销售退货单业务对象 sales_refund
 *
 * @date 2025-03-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SalesRefund.class, reverseConvertGenerate = false)
public class SalesRefundBo<T extends SalesRefundDetailBo> extends BaseRefundBo<T> {
}
