package com.ruoyi.erp.sales.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseRefundDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesRefundDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 销售退货单明细业务对象 Sales_refund_detail
 *
 * @date 2025-03-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SalesRefundDetail.class, reverseConvertGenerate = false)
public class SalesRefundDetailBo extends BaseRefundDetailBo {

}
