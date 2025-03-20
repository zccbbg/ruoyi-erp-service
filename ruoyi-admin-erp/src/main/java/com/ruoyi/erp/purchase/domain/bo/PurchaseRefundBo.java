package com.ruoyi.erp.purchase.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseOrderBo;
import com.ruoyi.erp.base.domain.bo.BaseRefundBo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseRefund;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 采购退货单业务对象 purchase_refund
 *
 * @author zcc
 * @date 2025-02-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PurchaseRefund.class, reverseConvertGenerate = false)
public class PurchaseRefundBo<T extends PurchaseRefundDetailBo> extends BaseRefundBo<T> {
    private Long warehouseId;
}
